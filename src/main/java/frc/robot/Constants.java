//----------------------[Package]----------------------//
package frc.robot;
//----------------------[Library]----------------------//
import java.lang.reflect.InvocationTargetException;
import java.lang.NullPointerException;
import java.lang.SecurityException;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//---------------------[Constants]---------------------//
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
                public static final Double L_KP = 0.1;

                public static final Double L_KI = 0.1;

                public static final Double L_KD = 0.1;

                public static final Double R_KP = 0.1;

                public static final Double R_KI = 0.1;

                public static final Double R_KD = 0.1;

            }
        }
    }

    public static final class Intake
    {

    }

    public static final class Climb
    {
        
    }

    public static final class Shooter
    {
        
    }

    /**
     * Testing a new System here...
     */
    public static final class DriverProfile
    {
        public static final class Cody_W
        {
            public static final Double JOYSTICK_X_DEADZONE = 5 * Math.pow(10,-2);
            public static final Double JOYSTICK_Y_DEADZONE = 5 * Math.pow(10,-2);
            public static final Double SPEED_COEFFICENT_SENSITIVIY = 1 * Math.pow(10,-2);
            public static final String TRIGGER_MODE_SWITCH = "a";
            public static final String TRIGGER_INCREMENT = "leftTrigger";
            public static final String TRIGGER_DECREMENT = "rightTrigger";
        }

        public static final class Alex_P
        {
            public static final Double JOYSTICK_X_DEADZONE = 5 * Math.pow(10,-2);
            public static final Double JOYSTICK_Y_DEADZONE = 5 * Math.pow(10,-2);
            public static final Double SPEED_COEFFICENT_SENSITIVIY = 1 * Math.pow(10,-2);
            public static final String TRIGGER_MODE_SWITCH = "a";
            public static final String TRIGGER_INCREMENT = "leftTrigger";
            public static final String TRIGGER_DECREMENT = "rightTrigger";
            
        }
    }

    /**
     * Don't ask how this works, or why I wrote it.
     * @author Cody Washington
     */
    public static final class Functions
    {
        public static Trigger deriveButton(Class<? extends CommandXboxController> Controller_Any, String MethodName)
        {try {return (Trigger)(Controller_Any.getClass().getMethod(MethodName).invoke(Controller_Any));}
        catch(NullPointerException | SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException exception){return null;}}
        public static Object deriveField(Class<?> Class_Any, String FieldName)
        {try{return Class_Any.getClass().getDeclaredField("TRIGGER_MODE_SWITCH").get(Class_Any.getClass().getDeclaredField("TRIGGER_MODE_SWITCH"));}
        catch(NoSuchFieldException | IllegalAccessException exception) {return null;}}
    }
}