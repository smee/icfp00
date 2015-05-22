package render;

import static java.lang.Math.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import model.GmlFunction;
import model.GmlInteger;
import model.TokenArray;
import scene.Light;
import scene.Shape;
import scene.Transform;
import scene.Triple;

public class Renderer {

  private static final Triple Black = new Triple(0, 0, 0);
  private final Light[] lights;
  private final Triple ambLight;
  private final int depth;
  private Graphics2D workingG;
  private JPanel panel;
  private final Shape scene;
  private BufferedImage img;

  public Renderer(final Shape scene, final TokenArray lights, final Triple ambient, final GmlInteger depth) {
    this.scene = scene;
    // scene.applyTransform(moveCamera());
    this.ambLight = ambient;
    this.depth = depth.i;
    final int len = lights.length();
    this.lights = new Light[len];
    for (int i = 0; i < len; i++) {
      this.lights[i] = (Light) lights.get(i);
    }

  }

  /**
   * Renders with camera at 0,0,-1 pointing at 0,0,0
   * 
   * @param fov
   * @param width
   * @param height
   * @param filename
   */
  public void render(final double fov, final int width, final int height, final String filename) {
	int num=Runtime.getRuntime().availableProcessors();
	System.out.printf("Running on %d cores.\n",num);
	final ExecutorService exec = Executors.newFixedThreadPool(num);

    createFrame(width, height, filename);
    final double w = 2 * Math.tan(Math.toRadians(0.5 * fov)); // width in world
    // coords
    final double deltaX = w / width; // je x pixel

    final double x0 = -w / 2;
    final double y0 = deltaX * height / 2;

    final int[] lines = createColumnsPermutation(width);
    final Triple origin = new Triple(0, 0, -1);

    for (int i = 0; i < width; i++) {
      final int crntCol = lines[i];
      exec.execute(new Runnable() {
        public void run() {
          for (int j = 0; j < height; j++) {

            final Color pxl = renderPixel(origin, x0 + (crntCol + 0.5) * deltaX, y0 - (j + 0.5) * deltaX);
            updateImage(crntCol, j, pxl);

          }
          panel.repaint();
          panel.revalidate();
        }
      });
    }
    exec.execute(new Runnable() {
      public void run() {
        try {
          ImageIO.write(img, "png", new File(filename + ".png"));
        } catch (final IOException e) {
          e.printStackTrace();
        }
      }
    });
    // wait 1000 days before returning;
    try {
      exec.shutdown();
      exec.awaitTermination(1000, TimeUnit.DAYS);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

  private Triple calculateColor(final Hit hit, final Triple dir, final int recursionDepth) {
    // 1. shadow ray, no shadow => phong
    // 2. reflection
    // 3. refraction
    final LightParameters surf = hit.getColor();
    final Triple c = surf.color;
    final Triple normal = hit.getNormal();
    final Triple hitPoint = hit.getHitPoint();

    double x = surf.kDiffuse * ambLight.x * c.x;
    double y = surf.kDiffuse * ambLight.y * c.y;
    double z = surf.kDiffuse * ambLight.z * c.z;

    double xsum, ysum, zsum;
    xsum = ysum = zsum = 0;
    for (final Light light : lights) {
      // diffuse
      final Triple L = light.dir;
      final Triple newOrigin = hitPoint;
      final Triple lightDirection = L.mul(-1);

      final Hit shadowHit = scene.getIntersection(new Ray(newOrigin, lightDirection, hit.getShape()));
      // System.out.println(shadowHit.getHitPoint().mul(-1).add(hitPoint));
      if (shadowHit == null) {// nix im weg oder acne (shadow auf sich selbst)
        final double diffFactor = normal.dotProduct(L) * surf.kDiffuse;
        if (diffFactor > 0) {
          xsum += diffFactor * light.color.x * c.x;
          ysum += diffFactor * light.color.y * c.y;
          zsum += diffFactor * light.color.z * c.z;
        }
        // specular
        final Triple H = dir.add(L).mul(0.5);// half way between light and
        // view
        // vector
        final double kSpec = Math.pow(normal.dotProduct(H), surf.phongExp) * surf.kSpecular;
        if (kSpec > 0) {
          final Triple lightIntensity = light.color;
          xsum += kSpec * lightIntensity.x * c.x;
          ysum += kSpec * lightIntensity.y * c.y;
          zsum += kSpec * lightIntensity.z * c.z;
        }
      }
      // reflection
      if (recursionDepth < this.depth) {
        // reflection vector = view - 2*view.normal*normal
        final Triple reflectionVector = dir.add(normal.mul(-2 * dir.dotProduct(normal)));
        final Triple reflectedLight = this.findColorForRay(new Ray(newOrigin, reflectionVector, hit.getShape()),
            recursionDepth + 1);
        xsum += surf.kSpecular * reflectedLight.x * c.x;
        ysum += surf.kSpecular * reflectedLight.y * c.y;
        zsum += surf.kSpecular * reflectedLight.z * c.z;

      }

    }
    x += xsum;
    y += ysum;
    z += zsum;
    return new Triple(x, y, z);
  }

  private int[] createColumnsPermutation(final int width) {
    // create random permutation of lines to render
    final int[] lines = new int[width];
    for (int i = 0; i < lines.length; lines[i] = i, i++) {
      ;
    }
    if (true) {
      final Random r = new Random();
      for (int i = 1; i < lines.length; i++) {
        final int j = r.nextInt(i + 1);
        final int temp = lines[j];
        lines[j] = lines[i];
        lines[i] = temp;
      }
    }
    return lines;
  }

  private void createFrame(final int width, final int height, final String filename) {
    final JFrame frame = new JFrame(filename);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setSize(width + 10, height + 10);
    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    this.workingG = (Graphics2D) img.getGraphics();
    workingG.setBackground(Color.BLACK);
    workingG.clearRect(0, 0, width, height);
    this.panel = new JPanel() {
      @Override
      public void paint(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, 0, 0, null);
      }
    };
    JScrollPane jsp = new JScrollPane(panel);
    frame.getContentPane().add(jsp);
    frame.pack();
    frame.setSize(width, height);
    frame.setVisible(true);
  }

  private Triple findColorForRay(final Ray ray, final int recDepth) {
    // find nearest intersection
    final Hit hit = scene.getIntersection(ray);

    if ((hit == null) || (hit.getShape() == ray.getOriginShape())) {
      return Renderer.Black;
    } else {

      final Triple colPart = calculateColor(hit, ray.getDirection(), recDepth);
      return colPart;
    }
  }

  private Transform moveCamera() {
    final Triple vEye = new Triple(0, 0, -1);
    final Triple vLookAt = new Triple(0, 0, -1);
    final Triple vUp = new Triple(0, 1, 0);
    final Triple vN = vEye.sub(vLookAt).normalize();
    final Triple vU = vUp.crossProduct(vN);
    final Triple vV = vN.crossProduct(vU);

    final double[][] id = new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
    final double[][] cameraTransform = new double[4][];
    cameraTransform[0] = new double[]{vU.x, vV.x, vN.x, 0};
    cameraTransform[1] = new double[]{vU.y, vV.y, vN.y, 0};
    cameraTransform[2] = new double[]{vU.z, vV.z, vN.z, 0};
    cameraTransform[3] = new double[]{vEye.dotProduct(vU) * -1, vEye.dotProduct(vV) * -1, vEye.dotProduct(vN) * -1, 1};
    final double[][] inv = new double[4][];
    inv[0] = new double[]{vU.x, vV.x, vN.x, 0};
    inv[1] = new double[]{vU.y, vV.y, vN.y, 0};
    inv[2] = new double[]{vU.z, vV.z, vN.z, 0};
    inv[3] = new double[]{vEye.dotProduct(vU) * -1, vEye.dotProduct(vV) * -1, vEye.dotProduct(vN) * -1, 1};
    Transform.invert(inv);

    return new Transform(cameraTransform, inv, id, id);
  }

  private Color renderPixel(final Triple origin, final double x, final double y) {
    final Triple dir = new Triple(x, y, 1).normalize();

    return toColor(findColorForRay(new Ray(origin, dir, new scene.Sphere((GmlFunction) null)), 1));
  }

  private Color toColor(final Triple c) {

    return new Color((int) (255 * min(c.x, 1)), (int) (255 * min(c.y, 1)), (int) (255 * min(c.z, 1)));

  }

  private void updateImage(final int x, final int y, final Color pxl) {
    img.setRGB(x, y, pxl.getRGB());
  }

}
