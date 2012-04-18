package scene;


public class Transform {

    private double[][] mat;
    private double[][] inv;
    private double[][] vmat;
    private double[][] vinv;

    public Transform(double[][] mat, double[][] inv, double[][] vmat, double[][]vinv) {
      this.mat = mat;
      this.inv = inv;
      this.vmat = vmat;
      this.vinv = vinv;
    }

    public Transform apply(Transform t) {
      double[][] rmat = new double[4][4];
      for (int i=0;i<4;i++)
        for (int j=0;j<4;j++) {
          double total = 0;
          for (int k=0; k<4; k++)
            total += this.mat[i][k]*t.mat[k][j];
          rmat[i][j] = total;
        }
      double[][] rinv = new double[4][4];
      for (int i=0;i<4;i++)
        for (int j=0;j<4;j++) {
          double total = 0;
          for (int k=0; k<4; k++)
            total += this.inv[k][j]*t.inv[i][k]; //Opposite order
          rinv[i][j] = total;
        }
      double[][] vrmat = new double[4][4];
      for (int i=0;i<4;i++)
        for (int j=0;j<4;j++) {
          double total = 0;
          for (int k=0; k<4; k++)
            total += this.vmat[i][k]*t.vmat[k][j];
          vrmat[i][j] = total;
        }
      double[][] vrinv = new double[4][4];
      for (int i=0;i<4;i++)
        for (int j=0;j<4;j++) {
          double total = 0;
          for (int k=0; k<4; k++)
            total += this.vinv[k][j]*t.vinv[i][k]; //Opposite order
          vrinv[i][j] = total;
        }
      return new Transform(rmat, rinv, vrmat, vrinv);
    }

    public Triple applyTriple(Triple p) {
      return new Triple(
              mat[0][0]*p.x + mat[0][1]*p.y + mat[0][2]*p.z + mat[0][3],
              mat[1][0]*p.x + mat[1][1]*p.y + mat[1][2]*p.z + mat[1][3],
              mat[2][0]*p.x + mat[2][1]*p.y + mat[2][2]*p.z + mat[2][3]);

    }

    public Triple inverseTriple(Triple p) {
      return new Triple(
              inv[0][0]*p.x + inv[0][1]*p.y + inv[0][2]*p.z + inv[0][3],
              inv[1][0]*p.x + inv[1][1]*p.y + inv[1][2]*p.z + inv[1][3],
              inv[2][0]*p.x + inv[2][1]*p.y + inv[2][2]*p.z + inv[2][3]);
    }

    public Triple applyVector(Triple p) {
      return new Triple(
              mat[0][0]*p.x + mat[0][1]*p.y + mat[0][2]*p.z,
              mat[1][0]*p.x + mat[1][1]*p.y + mat[1][2]*p.z,
              mat[2][0]*p.x + mat[2][1]*p.y + mat[2][2]*p.z);
    }

    public Triple inverseVector(Triple p) {
      return new Triple(
              inv[0][0]*p.x + inv[0][1]*p.y + inv[0][2]*p.z,
              inv[1][0]*p.x + inv[1][1]*p.y + inv[1][2]*p.z,
              inv[2][0]*p.x + inv[2][1]*p.y + inv[2][2]*p.z);
    }

    public Triple applyNormVector(Triple p) {
      return new Triple(
              vmat[0][0]*p.x + vmat[0][1]*p.y + vmat[0][2]*p.z,
              vmat[1][0]*p.x + vmat[1][1]*p.y + vmat[1][2]*p.z,
              vmat[2][0]*p.x + vmat[2][1]*p.y + vmat[2][2]*p.z);
    }

    public Triple inverseNormVector(Triple p) {
      return new Triple(
              vinv[0][0]*p.x + vinv[0][1]*p.y + vinv[0][2]*p.z,
              vinv[1][0]*p.x + vinv[1][1]*p.y + vinv[1][2]*p.z,
              vinv[2][0]*p.x + vinv[2][1]*p.y + vinv[2][2]*p.z);
    }

    public static Transform getIdentity() {
      return new Transform(new double[][] {
          {1,0,0,0},
          {0,1,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,0},
          {0,1,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,0},
          {0,1,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,0},
          {0,1,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }
      );
    }

    public static Transform getRotateX(double degrees) {
      double cos = Math.cos(Math.toRadians(degrees));
      double sin = Math.sin(Math.toRadians(degrees));
      return new Transform(new double[][] {
          {1,0,0,0},
          {0,cos,-sin,0},
          {0,sin,cos,0},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,0},
          {0,cos,sin,0},
          {0,-sin,cos,0},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,0},
          {0,cos,-sin,0},
          {0,sin,cos,0},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,0},
          {0,cos,sin,0},
          {0,-sin,cos,0},
          {0,0,0,1}
        }
      );
    }

    public static Transform getRotateY(double degrees) {
      double cos = Math.cos(Math.toRadians(degrees));
      double sin = Math.sin(Math.toRadians(degrees));
      return new Transform(new double[][] {
          {cos,0,sin,0},
          {0,1,0,0},
          {-sin,0,cos,0},
          {0,0,0,1}
        }, new double[][] {
          {cos,0,-sin,0},
          {0,1,0,0},
          {sin,0,cos,0},
          {0,0,0,1}
        },new double[][] {
          {cos,0,sin,0},
          {0,1,0,0},
          {-sin,0,cos,0},
          {0,0,0,1}
        }, new double[][] {
          {cos,0,-sin,0},
          {0,1,0,0},
          {sin,0,cos,0},
          {0,0,0,1}
        }
      );
    }

    public static Transform getRotateZ(double degrees) {
      double cos = Math.cos(Math.toRadians(degrees));
      double sin = Math.sin(Math.toRadians(degrees));
      return new Transform(new double[][] {
          {cos,-sin,0,0},
          {sin,cos,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }, new double[][] {
          {cos,sin,0,0},
          {-sin,cos,0,0},
          {0,0,1,0},
          {0,0,0,1}
        },new double[][] {
          {cos,-sin,0,0},
          {sin,cos,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }, new double[][] {
          {cos,sin,0,0},
          {-sin,cos,0,0},
          {0,0,1,0},
          {0,0,0,1}
        }
      );
    }

    public static Transform getTranslate(Triple p) {
      return new Transform(new double[][] {
          {1,0,0,p.x},
          {0,1,0,p.y},
          {0,0,1,p.z},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,-p.x},
          {0,1,0,-p.y},
          {0,0,1,-p.z},
          {0,0,0,1}
        },new double[][] {
          {1,0,0,p.x},
          {0,1,0,p.y},
          {0,0,1,p.z},
          {0,0,0,1}
        }, new double[][] {
          {1,0,0,-p.x},
          {0,1,0,-p.y},
          {0,0,1,-p.z},
          {0,0,0,1}
        }
      );
    }

    public static Transform getScale(Triple p) {
      return new Transform(new double[][] {
          {p.x,0,0,0},
          {0,p.y,0,0},
          {0,0,p.z,0},
          {0,0,0,1}
        }, new double[][] {
          {1/p.x,0,0,0},
          {0,1/p.y,0,0},
          {0,0,1/p.z,0},
          {0,0,0,1}
        },new double[][] {
          {1/p.x,0,0,0},
          {0,1/p.y,0,0},
          {0,0,1/p.z,0},
          {0,0,0,1}
        }, new double[][] {
          {p.x,0,0,0},
          {0,p.y,0,0},
          {0,0,p.z,0},
          {0,0,0,1}
        }
      );
    }
    private static double determinant(double[][] m) {
    	   double value;
    	   value =
    	   m[0][3] * m[1][2] * m[2][1] * m[3][0]-m[0][2] * m[1][3] * m[2][1] * m[3][0]-m[0][3] * m[1][1] * m[2][2] * m[3][0]+m[0][1] * m[1][3]    * m[2][2] * m[3][0]+
    	   m[0][2] * m[1][1] * m[2][3] * m[3][0]-m[0][1] * m[1][2] * m[2][3] * m[3][0]-m[0][3] * m[1][2] * m[2][0] * m[3][1]+m[0][2] * m[1][3]    * m[2][0] * m[3][1]+
    	   m[0][3] * m[1][0] * m[2][2] * m[3][1]-m[0][0] * m[1][3] * m[2][2] * m[3][1]-m[0][2] * m[1][0] * m[2][3] * m[3][1]+m[0][0] * m[1][2]    * m[2][3] * m[3][1]+
    	   m[0][3] * m[1][1] * m[2][0] * m[3][2]-m[0][1] * m[1][3] * m[2][0] * m[3][2]-m[0][3] * m[1][0] * m[2][1] * m[3][2]+m[0][0] * m[1][3]    * m[2][1] * m[3][2]+
    	   m[0][1] * m[1][0] * m[2][3] * m[3][2]-m[0][0] * m[1][1] * m[2][3] * m[3][2]-m[0][2] * m[1][1] * m[2][0] * m[3][3]+m[0][1] * m[1][2]    * m[2][0] * m[3][3]+
    	   m[0][2] * m[1][0] * m[2][1] * m[3][3]-m[0][0] * m[1][2] * m[2][1] * m[3][3]-m[0][1] * m[1][0] * m[2][2] * m[3][3]+m[0][0] * m[1][1]    * m[2][2] * m[3][3];
    	   return value;
    	   } 
    public static final void invert(double[][] m) {
    	   m[0][0] = m[1][2]*m[2][3]*m[3][1] - m[1][3]*m[2][2]*m[3][1] + m[1][3]*m[2][1]*m[3][2] - m[1][1]*m[2][3]*m[3][2] - m[1][2]*m[2][1]*m[3][3] + m[1][1]*m[2][2]*m[3][3];
    	   m[0][1] = m[0][3]*m[2][2]*m[3][1] - m[0][2]*m[2][3]*m[3][1] - m[0][3]*m[2][1]*m[3][2] + m[0][1]*m[2][3]*m[3][2] + m[0][2]*m[2][1]*m[3][3] - m[0][1]*m[2][2]*m[3][3];
    	   m[0][2] = m[0][2]*m[1][3]*m[3][1] - m[0][3]*m[1][2]*m[3][1] + m[0][3]*m[1][1]*m[3][2] - m[0][1]*m[1][3]*m[3][2] - m[0][2]*m[1][1]*m[3][3] + m[0][1]*m[1][2]*m[3][3];
    	   m[0][3] = m[0][3]*m[1][2]*m[2][1] - m[0][2]*m[1][3]*m[2][1] - m[0][3]*m[1][1]*m[2][2] + m[0][1]*m[1][3]*m[2][2] + m[0][2]*m[1][1]*m[2][3] - m[0][1]*m[1][2]*m[2][3];
    	   m[1][0] = m[1][3]*m[2][2]*m[3][0] - m[1][2]*m[2][3]*m[3][0] - m[1][3]*m[2][0]*m[3][2] + m[1][0]*m[2][3]*m[3][2] + m[1][2]*m[2][0]*m[3][3] - m[1][0]*m[2][2]*m[3][3];
    	   m[1][1] = m[0][2]*m[2][3]*m[3][0] - m[0][3]*m[2][2]*m[3][0] + m[0][3]*m[2][0]*m[3][2] - m[0][0]*m[2][3]*m[3][2] - m[0][2]*m[2][0]*m[3][3] + m[0][0]*m[2][2]*m[3][3];
    	   m[1][2] = m[0][3]*m[1][2]*m[3][0] - m[0][2]*m[1][3]*m[3][0] - m[0][3]*m[1][0]*m[3][2] + m[0][0]*m[1][3]*m[3][2] + m[0][2]*m[1][0]*m[3][3] - m[0][0]*m[1][2]*m[3][3];
    	   m[1][3] = m[0][2]*m[1][3]*m[2][0] - m[0][3]*m[1][2]*m[2][0] + m[0][3]*m[1][0]*m[2][2] - m[0][0]*m[1][3]*m[2][2] - m[0][2]*m[1][0]*m[2][3] + m[0][0]*m[1][2]*m[2][3];
    	   m[2][0] = m[1][1]*m[2][3]*m[3][0] - m[1][3]*m[2][1]*m[3][0] + m[1][3]*m[2][0]*m[3][1] - m[1][0]*m[2][3]*m[3][1] - m[1][1]*m[2][0]*m[3][3] + m[1][0]*m[2][1]*m[3][3];
    	   m[2][1] = m[0][3]*m[2][1]*m[3][0] - m[0][1]*m[2][3]*m[3][0] - m[0][3]*m[2][0]*m[3][1] + m[0][0]*m[2][3]*m[3][1] + m[0][1]*m[2][0]*m[3][3] - m[0][0]*m[2][1]*m[3][3];
    	   m[2][2] = m[0][1]*m[1][3]*m[3][0] - m[0][3]*m[1][1]*m[3][0] + m[0][3]*m[1][0]*m[3][1] - m[0][0]*m[1][3]*m[3][1] - m[0][1]*m[1][0]*m[3][3] + m[0][0]*m[1][1]*m[3][3];
    	   m[2][3] = m[0][3]*m[1][1]*m[2][0] - m[0][1]*m[1][3]*m[2][0] - m[0][3]*m[1][0]*m[2][1] + m[0][0]*m[1][3]*m[2][1] + m[0][1]*m[1][0]*m[2][3] - m[0][0]*m[1][1]*m[2][3];
    	   m[3][0] = m[1][2]*m[2][1]*m[3][0] - m[1][1]*m[2][2]*m[3][0] - m[1][2]*m[2][0]*m[3][1] + m[1][0]*m[2][2]*m[3][1] + m[1][1]*m[2][0]*m[3][2] - m[1][0]*m[2][1]*m[3][2];
    	   m[3][1] = m[0][1]*m[2][2]*m[3][0] - m[0][2]*m[2][1]*m[3][0] + m[0][2]*m[2][0]*m[3][1] - m[0][0]*m[2][2]*m[3][1] - m[0][1]*m[2][0]*m[3][2] + m[0][0]*m[2][1]*m[3][2];
    	   m[3][2] = m[0][2]*m[1][1]*m[3][0] - m[0][1]*m[1][2]*m[3][0] - m[0][2]*m[1][0]*m[3][1] + m[0][0]*m[1][2]*m[3][1] + m[0][1]*m[1][0]*m[3][2] - m[0][0]*m[1][1]*m[3][2];
    	   m[3][3] = m[0][1]*m[1][2]*m[2][0] - m[0][2]*m[1][1]*m[2][0] + m[0][2]*m[1][0]*m[2][1] - m[0][0]*m[1][2]*m[2][1] - m[0][1]*m[1][0]*m[2][2] + m[0][0]*m[1][1]*m[2][2];
    	   double det = determinant(m);
    	   for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j]=m[i][j]/det;
			}
		}
    	  
    	}
}
