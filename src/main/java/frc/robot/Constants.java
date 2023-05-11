//----------------------[Package]----------------------//
package frc.robot;
//----------------------[Library]----------------------//
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.lang.NullPointerException;
import java.lang.SecurityException;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//---------------------[Constants]---------------------//
/** Constants for Magnus */
public final class Constants 
{
    public static final class Drive
    {              
        public static final Boolean IS_SIMULATION = false;
             
        public static final class HardwareInformation
        {
            public static final Double WHEEL_DIAMETER = 1.0;

            public static final Double[] DIMENSIONS = {1.0,1.0};

            public static final Integer ENCODER_RESOLUTION = 2048;

            public static final Double ENCODER_TICK_TO_METER_FACTOR = (2.0 * Math.PI * (HardwareInformation.WHEEL_DIAMETER/2) / HardwareInformation.ENCODER_RESOLUTION);
        }

        public static final class MotorPorts
        {
            public static final Integer FL = 12;

            public static final Integer FR = 13;

            public static final Integer RL = 11;

            public static final Integer RR = 14;
        }

        public static final class Data
        {
            public static final class PID
            {
                public static final Double DB_KP = 0.1;

                public static final Double DB_KI = 0.1;

                public static final Double DB_KD = 0.1;
            }
        }
    }

    public static final class Intake
    {
        public static final class MotorPorts
        {
            public static final Integer II = 21;

            public static final Integer IL = 51;

            public static final Integer IR = 52;

            public static final Integer IM = 2;
        }
        public static final class SolenoidChannels
        {
            public static final Integer[] SONE = {0,7};

            public static final Integer[] STWO = {1,6};

        }
    }

    public static final class Climb
    {
        public static final class MotorPorts
        {
            public static final Integer LW = 43;

            public static final Integer LA = 41;

            public static final Integer RW = 44;

            public static final Integer RA = 42;
        }
    }

    public static final class Shooter
    {
        //TODO: Armaan Shooter Constants Here
    }

    /**
     * Testing a new System here...
     */
    public static final class DriverProfile
    {
        public static final Class<?> DRIVER_PROFILE = Cody_W.class; // <------- TODO: Change as Needed
        public static final Integer DRIVER_CONTROLLER_PORT = 0; // <------- TODO: Change as Needed

        public static final class Cody_W
        {
            public static final Double JOYSTICK_X_DEADZONE = (5) * (Math.pow(10,-2));
            public static final Double JOYSTICK_Y_DEADZONE = (5) * (Math.pow(10,-2));
            public static final Double SPEED_COEFFICIENT_SENSITIVITY = (1) * (Math.pow(10,-2));
            public static final String TRIGGER_INCREMENT = "leftTrigger";
            public static final String TRIGGER_DECREMENT = "rightTrigger";
            public static final String TRIGGER_INTAKE_IN = "leftBumper";
            public static final String TRIGGER_INTAKE_OUT = "rightBumper";
            public static final String TRIGGER_INTAKE_STOP = "x";
            public static final String TRIGGER_MODE_SWITCH = "y";            
            public static final String TRIGGER_FIRE_OBJECT = "a";
        }

        public static final class Alex_P
        {
            public static final Double JOYSTICK_X_DEADZONE = (5) * (Math.pow(10,-2));
            public static final Double JOYSTICK_Y_DEADZONE = (5) * (Math.pow(10,-2));
            public static final Double SPEED_COEFFICIENT_SENSITIVITY = (1) * (Math.pow(10,-2));
            public static final String TRIGGER_INCREMENT = "leftTrigger";
            public static final String TRIGGER_DECREMENT = "rightTrigger";
            public static final String TRIGGER_INTAKE_IN = "leftBumper";
            public static final String TRIGGER_INTAKE_OUT = "rightBumper";
            public static final String TRIGGER_INTAKE_STOP = "x";
            public static final String TRIGGER_MODE_SWITCH = "y";            
            public static final String TRIGGER_FIRE_OBJECT = "a";
        }

        public static final class Default 
        {
            public static final Integer PRIMARY_CONTROLLER_PORT = 0;
            public static final CommandXboxController PRIMARY_CONTROLLER = new CommandXboxController(PRIMARY_CONTROLLER_PORT);
            public static final Double JOYSTICK_X_DEADZONE = (5) * (Math.pow(10,-2));
            public static final Double JOYSTICK_Y_DEADZONE = (5) * (Math.pow(10,-2));
            public static final Double SPEED_COEFFICIENT_SENSITIVITY = (5) * (Math.pow(10,-2));
            public static final Trigger TRIGGER_INCREMENT = PRIMARY_CONTROLLER.leftTrigger();
            public static final Trigger TRIGGER_DECREMENT = PRIMARY_CONTROLLER.rightTrigger();
            public static final Trigger TRIGGER_INTAKE_IN = PRIMARY_CONTROLLER.leftBumper();
            public static final Trigger TRIGGER_INTAKE_OUT = PRIMARY_CONTROLLER.rightBumper();
            public static final Trigger TRIGGER_INTAKE_STOP = PRIMARY_CONTROLLER.x();
            public static final Trigger TRIGGER_MODE_SWITCH = PRIMARY_CONTROLLER.y();
            public static final Trigger TRIGGER_SHOOTER_PID_ON = PRIMARY_CONTROLLER.a();
            public static final Trigger TRIGGER_SHOOTER_PID_OFF = PRIMARY_CONTROLLER.b();

        }
        
        public static final class ButtonBoard
        {
            public static final Integer BUTTON_WINCH_OUT = 4;
            public static final Integer BUTTON_WINCH_IN = 6;
            public static final Integer BUTTON_ARM_DOWN = 3;
            public static final Integer BUTTON_ARM_UP = 5;
            public static final Integer BUTTON_SHOOT = 1;
        }
    }

        /**
         * Don't ask how this works, or why I wrote it.
         * @author Cody Washington
         */
        public static final class Functions
        {
            /**
             * retrieves a method from a class and executes it regardless of the method's protection level
             * @param Object_Any - Object to invoke on
             * @param MethodName - Name of Method
             * @param Arguments_Any - Arguments of Method
             * @return the result of the Invoked Method
             */
            public static Object getMethodAndExecute(Object Object_Any, String MethodName, Object... Arguments_Any)
            {var ArgumentTypes = Arrays.stream(Arguments_Any).map(Object::getClass).toArray(Class<?>[]::new);
            if(ArgumentTypes.length > 0) {try{var MethodCapture = Object_Any.getClass().getDeclaredMethod(MethodName, ArgumentTypes); MethodCapture.setAccessible(true);
            var MethodReturn =  MethodCapture.invoke(Object_Any.getClass(), Arguments_Any); return (MethodReturn == (null)) ? Void.class : MethodReturn;}
            catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {exception.printStackTrace();return null;}}
            else {try{var MethodCapture = Object_Any.getClass().getDeclaredMethod(MethodName); MethodCapture.setAccessible(true);
            var MethodReturn = MethodCapture.invoke(Object_Any); return (MethodReturn == (null)) ? Void.class : MethodReturn;}
            catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {exception.printStackTrace();return null;}}}
            /**
             * retrieves a method from a class and executes it regardless of the method's protection level
             * @param Class_Any - Class of Field
             * @param FieldName - Name of Field
             * @return the value of the Field
             */
            public static Object getFieldValue(Class<?> Class_Any, String FieldName)
            {try {return(Class_Any.getDeclaredField(FieldName).get(Class_Any));}
            catch(NullPointerException | SecurityException | NoSuchFieldException | IllegalAccessException exception) {exception.printStackTrace(); return null;}}
            public static Double norm(Double a, Double b) 
            {return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));}
        }
}