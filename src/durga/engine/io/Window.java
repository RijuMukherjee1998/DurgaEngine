package durga.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
	private int width,height;
	private String title;
	public long window;
	public Input input;
	
	public Window(int width,int height,String title) {
		this.width=width;
		this.height=height;
		this.title=title;
	}
	
	private void init() {
		if(!GLFW.glfwInit()) {
			System.out.println("Error window not initialized...");
			return;
		}
		
		input = new Input();
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		
		if(window == 0) {
			System.err.println("ERROR window can't be opened...");
			return;
		}
		
		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
		GLFW.glfwSetWindowPos(window, 
				(vidmode.width()-width)/2, 
				(vidmode.height()-height/2));
		
		//Make the openGl context to the current window
		GLFW.glfwMakeContextCurrent(window);
		
		//Enables v-sync
		GLFW.glfwSwapInterval(1);
		
		//Make the window visible
		GLFW.glfwShowWindow(window);
		
		//Arranging for inputs
		GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
		GLFW.glfwSetCursorPosCallback(window,input.getMousePointerCallback());
		GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());
		
		
		
	}
	
	private void loop() {
		
		//LWJGL detects the current context
		GL.createCapabilities();
		
		//clears the previous color  and sets new color
		GL11.glClearColor(0.2f, 0.4f, 0.5f, 1.0f);
		
		
		
		while(!GLFW.glfwWindowShouldClose(window)) {
			//clears the frame buffers
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			//swap the color buffers
			GLFW.glfwSwapBuffers(window);
			
			//Poll for window events
			GLFW.glfwPollEvents();
			
			if(input.isKeyDown(GLFW.GLFW_KEY_ESCAPE) == true || input.isButttonDown(GLFW.GLFW_MOUSE_BUTTON_1) == true) {
				return;
			}
			
		}
		input.destroyAllCallbacks();
	}
	
	public void create() {
		try {
		init();
		loop();
		
		//GLFW.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
		
		}
		finally {
		//GLFW.glfwSetErrorCallback(null).free();
		GLFW.glfwTerminate();
		
		}
		
	}
}
