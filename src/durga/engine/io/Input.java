package durga.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;


public class Input {
	private  boolean [] keys =new boolean[GLFW.GLFW_KEY_LAST];
	private  boolean [] buttons =new boolean [GLFW.GLFW_MOUSE_BUTTON_LAST];
	private double mouseX,mouseY;
	private GLFWKeyCallback keyboard;
	private GLFWCursorPosCallback mousePointer;
	private GLFWMouseButtonCallback mouseButton;
	
	public Input() {
		
		
		keyboard = new GLFWKeyCallback() {
			@Override
			public void invoke(long window,int key,int scancode, int action, int mode) {
				keys[key] = (action != GLFW.GLFW_RELEASE);
			}
			
		};
		
		mousePointer = new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xpos, double ypos) {
				mouseX=xpos;
				mouseY=ypos;
				
			}
		};
		
		mouseButton = new GLFWMouseButtonCallback() {

			@Override
			public void invoke(long window, int button, int action, int mods) {
				// TODO Auto-generated method stub
				buttons[button] = (action!=GLFW.GLFW_RELEASE);
			}
			
		};
		
		
	}

	
	public void destroyAllCallbacks() {
		keyboard.free();
		mousePointer.free();
		mouseButton.free();
	}
	public boolean isKeyDown(int key) {
		return keys[key];
	}

	public  boolean isButttonDown(int button) {
		return buttons[button];
	}

	public double getMouseX() {
		return mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public GLFWKeyCallback getKeyboardCallback() {
		return keyboard;
	}

	public GLFWCursorPosCallback getMousePointerCallback() {
		return mousePointer;
	}

	public GLFWMouseButtonCallback getMouseButtonCallback() {
		return mouseButton;
	}
}
