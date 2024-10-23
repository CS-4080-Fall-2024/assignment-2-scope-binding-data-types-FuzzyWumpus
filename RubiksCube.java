import java.util.Arrays;


public class RubiksCube {
    private int size; // size of the cube (e.g. 3 for 3x3)
    private char[][][] cube; // 3D array representing the cube

    // Face indices for reference
    private static final int FRONT = 0, BACK = 1, LEFT = 2, RIGHT = 3, TOP = 4, BOTTOM = 5;

    
    public RubiksCube(int size) {
        this.size = size;
        this.cube = new char[6][size][size]; // 6 faces of size x size
        initializeCube();
    }

    // Initialize cube with the default colors for each face
    private void initializeCube() {
        char[] colors = {'W', 'Y', 'G', 'B', 'R', 'O'}; // White, Yellow, Green, Blue, Red, Orange
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < size; row++) {
                Arrays.fill(cube[face][row], colors[face]);
            }
        }
    }

    // Method to rotate a face Right
    private void rotateFaceRight(int face) {
        char[][] newFace = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                newFace[col][size - 1 - row] = cube[face][row][col];
            }
        }
        cube[face] = newFace;
    }
    private void rotateFaceLeft(int face) {
        char[][] newFace = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                newFace[size - 1 - col][row] = cube[face][row][col];
            }
        }
        cube[face] = newFace;
    }

    // Horizontal Right movement
    // Rotate the top layer (affects front, left, back, right faces)
    public void rotateTopLayerRight() {
        rotateFaceRight(TOP); // Rotate top face

    
        char[] frontTop = Arrays.copyOf(cube[FRONT][0], size);
        char[] leftTop = Arrays.copyOf(cube[LEFT][0], size);
        char[] backTop = Arrays.copyOf(cube[BACK][0], size);
        char[] rightTop = Arrays.copyOf(cube[RIGHT][0], size);

      
        cube[FRONT][0] = leftTop;
        cube[LEFT][0] = backTop;
        cube[BACK][0] = rightTop;
        cube[RIGHT][0] = frontTop;
    }


    public void rotateMiddleLayerRight(int layer) {
        rotateFaceRight(TOP); // Rotate top face

        
        char[] frontMiddle = Arrays.copyOf(cube[FRONT][layer], size);
        char[] leftMiddle = Arrays.copyOf(cube[LEFT][layer], size);
        char[] backMiddle = Arrays.copyOf(cube[BACK][layer], size);
        char[] rightMiddle = Arrays.copyOf(cube[RIGHT][layer], size);

       
        cube[FRONT][layer] = leftMiddle;
        cube[LEFT][layer] = backMiddle;
        cube[BACK][layer] = rightMiddle;
        cube[RIGHT][layer] = frontMiddle;
    }


   
    public void rotateBottomLayerRight() {
        rotateFaceLeft(BOTTOM);

        char[] frontBottom = Arrays.copyOf(cube[FRONT][size - 1], size);
        char[] leftBottom = Arrays.copyOf(cube[LEFT][size - 1], size);
        char[] backBottom = Arrays.copyOf(cube[BACK][size - 1], size);
        char[] rightBottom = Arrays.copyOf(cube[RIGHT][size - 1], size);

        cube[FRONT][size - 1] = rightBottom;
        cube[RIGHT][size - 1] = backBottom;
        cube[BACK][size - 1] = leftBottom;
        cube[LEFT][size - 1] = frontBottom;
    }

    //Horizontal Left movements
    public void rotateTopLayerLeft() {
        rotateFaceLeft(TOP); // Rotate top face

       
        char[] frontTop = Arrays.copyOf(cube[FRONT][0], size);
        char[] leftTop = Arrays.copyOf(cube[LEFT][0], size);
        char[] backTop = Arrays.copyOf(cube[BACK][0], size);
        char[] rightTop = Arrays.copyOf(cube[RIGHT][0], size);

       
        cube[FRONT][0] = leftTop;
        cube[LEFT][0] = backTop;
        cube[BACK][0] = rightTop;
        cube[RIGHT][0] = frontTop;
    }


    public void rotateMiddleLayerLeft(int layer) {
        rotateFaceLeft(TOP); // Rotate top face

        
        char[] frontMiddle = Arrays.copyOf(cube[FRONT][layer], size);
        char[] leftMiddle = Arrays.copyOf(cube[LEFT][layer], size);
        char[] backMiddle = Arrays.copyOf(cube[BACK][layer], size);
        char[] rightMiddle = Arrays.copyOf(cube[RIGHT][layer], size);

      
        cube[FRONT][layer] = leftMiddle;
        cube[LEFT][layer] = backMiddle;
        cube[BACK][layer] = rightMiddle;
        cube[RIGHT][layer] = frontMiddle;
    }


    // Rotate the bottom layer
    public void rotateBottomLayerLeft() {
        rotateFaceLeft(BOTTOM); 

        char[] frontBottom = Arrays.copyOf(cube[FRONT][size - 1], size);
        char[] leftBottom = Arrays.copyOf(cube[LEFT][size - 1], size);
        char[] backBottom = Arrays.copyOf(cube[BACK][size - 1], size);
        char[] rightBottom = Arrays.copyOf(cube[RIGHT][size - 1], size);

        cube[FRONT][size - 1] = rightBottom;
        cube[RIGHT][size - 1] = backBottom;
        cube[BACK][size - 1] = leftBottom;
        cube[LEFT][size - 1] = frontBottom;
    }

    


public void rotateLeftLayerDown() {
    rotateFaceRight(LEFT); 

  
    char[] frontLeft = new char[size];
    char[] topLeft = new char[size];
    char[] backRight = new char[size]; // Back face column will be mirrored
    char[] bottomLeft = new char[size];

    for (int i = 0; i < size; i++) {
        frontLeft[i] = cube[FRONT][i][0];
        topLeft[i] = cube[TOP][i][0];
        backRight[i] = cube[BACK][i][size - 1];
        bottomLeft[i] = cube[BOTTOM][i][0];
    }

    for (int i = 0; i < size; i++) {
        cube[FRONT][i][0] = topLeft[i];
        cube[TOP][i][0] = backRight[size - 1 - i]; 
        cube[BACK][i][size - 1] = bottomLeft[size - 1 - i]; 
        cube[BOTTOM][i][0] = frontLeft[i];
    }
}


public void rotateRightLayerDown() {
    rotateFaceRight(RIGHT); // Rotate right face

  
    char[] frontRight = new char[size];
    char[] topRight = new char[size];
    char[] backLeft = new char[size]; 
    char[] bottomRight = new char[size];

    for (int i = 0; i < size; i++) {
        frontRight[i] = cube[FRONT][i][size - 1];
        topRight[i] = cube[TOP][i][size - 1];
        backLeft[i] = cube[BACK][i][0];
        bottomRight[i] = cube[BOTTOM][i][size - 1];
    }

    for (int i = 0; i < size; i++) {
        cube[FRONT][i][size - 1] = topRight[i];
        cube[TOP][i][size - 1] = backLeft[size - 1 - i]; 
        cube[BACK][i][0] = bottomRight[size - 1 - i]; 
        cube[BOTTOM][i][size - 1] = frontRight[i];
    }
}




public void rotateLeftLayerUp() {
    rotateFaceLeft(LEFT); 

    char[] frontLeft = new char[size];
    char[] topLeft = new char[size];
    char[] backRight = new char[size]; 
    char[] bottomLeft = new char[size];

    for (int i = 0; i < size; i++) {
        frontLeft[i] = cube[FRONT][i][0];
        topLeft[i] = cube[TOP][i][0];
        backRight[i] = cube[BACK][i][size - 1];
        bottomLeft[i] = cube[BOTTOM][i][0];
    }

    
    for (int i = 0; i < size; i++) {
        cube[FRONT][i][0] = bottomLeft[i];
        cube[BOTTOM][i][0] = backRight[size - 1 - i]; 
        cube[BACK][i][size - 1] = topLeft[size - 1 - i]; 
        cube[TOP][i][0] = frontLeft[i];
    }
}

public void rotateRightLayerUp() {
    rotateFaceLeft(RIGHT); 

    
    char[] frontRight = new char[size];
    char[] topRight = new char[size];
    char[] backLeft = new char[size];
    char[] bottomRight = new char[size];

    for (int i = 0; i < size; i++) {
        frontRight[i] = cube[FRONT][i][size - 1];
        topRight[i] = cube[TOP][i][size - 1];
        backLeft[i] = cube[BACK][i][0];
        bottomRight[i] = cube[BOTTOM][i][size - 1];
    }

  
    for (int i = 0; i < size; i++) {
        cube[FRONT][i][size - 1] = bottomRight[i];
        cube[BOTTOM][i][size - 1] = backLeft[size - 1 - i]; 
        cube[BACK][i][0] = topRight[size - 1 - i];
        cube[TOP][i][size - 1] = frontRight[i];
    }
}


    // UI to give the user a visual
    public void printCube() {
        for (int face = 0; face < 6; face++) {
            System.out.println("Face " + face + ":");
            for (int row = 0; row < size; row++) {
                System.out.println(Arrays.toString(cube[face][row]));
            }
            System.out.println();
        }
    }

    // Main method for testing (Lots of testing lol)
    public static void main(String[] args) {

        //Vetrical Movement
        RubiksCube cube = new RubiksCube(3); 
        cube.printCube(); 
    
        System.out.println("Rotating left layer down");
        cube.rotateLeftLayerDown();
        cube.printCube();

        System.out.println("Rotating right layer down");
        cube.rotateRightLayerDown();
        cube.printCube();

        System.out.println("Rotating left layer up");
        cube.rotateLeftLayerUp();
        cube.printCube();

        System.out.println("Rotating right layer up");
        cube.rotateRightLayerUp();
        cube.printCube();



        //Horizontal Movement
       
        System.out.println("Rotating top layer right");
        cube.rotateTopLayerRight();
        cube.printCube(); 

       
        System.out.println("Rotating layer 1 right");
        cube.rotateMiddleLayerRight(1);
        cube.printCube();

       
        System.out.println("Rotating bottom layer right");
        cube.rotateBottomLayerRight();
        cube.printCube(); 

       
         System.out.println("Rotating top layer left");
         cube.rotateTopLayerLeft();
         cube.printCube(); 
 
      
         System.out.println("Rotating layer 1 left");
         cube.rotateMiddleLayerLeft(1);
         cube.printCube();
 
       
         System.out.println("Rotating bottom layer left");
         cube.rotateBottomLayerLeft();
         cube.printCube(); 

    }
}
