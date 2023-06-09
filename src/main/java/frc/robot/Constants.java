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
public final class Constants {
    public static final class Drive {
        public static final Boolean IS_SIMULATION = false;

        public static final class HardwareInformation {
            public static final Double WHEEL_DIAMETER = 1.0;

            public static final Double[] DIMENSIONS = { 1.0, 1.0 };

            public static final Integer ENCODER_RESOLUTION = 2048;

            public static final Double ENCODER_TICK_TO_METER_FACTOR = (2.0 * Math.PI
                    * (HardwareInformation.WHEEL_DIAMETER / 2) / HardwareInformation.ENCODER_RESOLUTION);
        }

        public static final class MotorPorts {
            public static final Integer FL = 12;

            public static final Integer FR = 11;

            public static final Integer RL = 14;

            public static final Integer RR = 13;
        }

        public static final class Data {
            public static final class PID {
                public static final Double DB_KP = 0.1;

                public static final Double DB_KI = 0.1;

                public static final Double DB_KD = 0.1;
            }
        }
    }

    public static final class Intake {
        public static final class MotorPorts {
            public static final Integer II = 21;

            public static final Integer IL = 51;

            public static final Integer IR = 52;

            public static final Integer IM = 2;

            public static final Integer SM = 0;
        }

        public static final class Data {
            public static final Double Intake_Motor_In = (1.0);

            public static final Double Intake_Motor_Out = (-1.0);
        }

        public static final class SolenoidChannels {
            public static final Integer[] SONE = { 2, 7 };

            public static final Integer[] STWO = { 1, 6 };
        }
    }

    public static final class Climb {
        public static final class MotorPorts {
            public static final Integer LW = 43;

            public static final Integer LA = 41;

            public static final Integer RW = 44;

            public static final Integer RA = 42;
        }

        public static final class Values {
            public static final Double C_ARM_UP = (1.0);

            public static final Double C_ARM_DOWN = (0.5);

            public static final Double C_WIN_IN = (0.8);

            public static final Double C_WIN_OUT = (-0.8);
        }
    }

    public static final class Shooter {
        public static final class MotorPorts {
            public static final Integer LR = 31;

            public static final Integer LH = 33;

            public static final Integer RR = 32;

            public static final Integer RH = 34;
        }

        public static final class Data {
            public static final Double HOPPER_IN = (1.0);

            public static final Double HOPPER_OUT = (-1.0);

            public static final Double REGULAR_FIRE = (0.1);

            public static final Double RALLY_FIRE = (1.0);
        }
    }

    public static final class DriverProfile {
        public static final Class<?> DRIVER_PROFILE = (Cody_W.class);
        public static final Integer DRIVER_CONTROLLER_PORT = (0);

        public static final class ControllerButtons {
            public static final String BUTTON_A = "a";
            public static final String BUTTON_B = "b";
            public static final String BUTTON_X = "x";
            public static final String BUTTON_Y = "y";
            public static final String BUTTON_POV_LEFT = "povLeft";
            public static final String BUTTON_POV_UP = "povRight";
            public static final String BUTTON_POV_CENTER = "povCenter";
            public static final String BUTTON_POV_DOWN = "povUp";
            public static final String BUTTON_POV_RIGHT = "povDown";
            public static final String BUTTON_LEFT_TRIGGER = "leftTrigger";
            public static final String BUTTON_RIGHT_TRIGGER = "rightTrigger";
            public static final String BUTTON_LEFT_BUMPER = "leftBumper";
            public static final String BUTTON_RIGHT_BUMPER = "rightBumper";
        }
        public static final class Cody_W {
            public static final Double JOYSTICK_X_DEADZONE = (5) * (Math.pow(10, -2));
            public static final Double JOYSTICK_Y_DEADZONE = (5) * (Math.pow(10, -2));
            public static final Double SPEED_COEFFICIENT_SENSITIVITY = (1) * (Math.pow(10, -2));
            public static final String TRIGGER_INCREMENT = ControllerButtons.BUTTON_POV_LEFT;
            public static final String TRIGGER_DECREMENT = ControllerButtons.BUTTON_POV_RIGHT;
            public static final String TRIGGER_INTAKE_OUT = ControllerButtons.BUTTON_LEFT_TRIGGER;
            public static final String TRIGGER_INTAKE_IN = ControllerButtons.BUTTON_RIGHT_TRIGGER;
            public static final String TRIGGER_INTAKE_STOP = ControllerButtons.BUTTON_X;
            public static final String TRIGGER_FIRE_OBJECT = ControllerButtons.BUTTON_A;
            public static final String TRIGGER_MODE_SWITCH = ControllerButtons.BUTTON_B;
            public static final String TRIGGER_DB_MODE = ControllerButtons.BUTTON_Y;
            public static final String TRIGGER_L_ARM_CONTROL = ControllerButtons.BUTTON_LEFT_BUMPER;
            public static final String TRIGGER_R_ARM_CONTROL = ControllerButtons.BUTTON_RIGHT_BUMPER;
        }

        public static final class Alex_P {
            public static final Double JOYSTICK_X_DEADZONE = (5) * (Math.pow(10, -2));
            public static final Double JOYSTICK_Y_DEADZONE = (5) * (Math.pow(10, -2));
            public static final Double SPEED_COEFFICIENT_SENSITIVITY = (1) * (Math.pow(10, -2));
            public static final String TRIGGER_INCREMENT = ControllerButtons.BUTTON_POV_LEFT;
            public static final String TRIGGER_DECREMENT = ControllerButtons.BUTTON_POV_RIGHT;
            public static final String TRIGGER_INTAKE_OUT = ControllerButtons.BUTTON_LEFT_TRIGGER;
            public static final String TRIGGER_INTAKE_IN = ControllerButtons.BUTTON_RIGHT_TRIGGER;
            public static final String TRIGGER_INTAKE_STOP = ControllerButtons.BUTTON_X;
            public static final String TRIGGER_FIRE_OBJECT = ControllerButtons.BUTTON_A;
            public static final String TRIGGER_MODE_SWITCH = ControllerButtons.BUTTON_B;
            public static final String TRIGGER_DB_MODE = ControllerButtons.BUTTON_Y;
            public static final String TRIGGER_L_ARM_CONTROL = ControllerButtons.BUTTON_LEFT_BUMPER;
            public static final String TRIGGER_R_ARM_CONTROL = ControllerButtons.BUTTON_RIGHT_BUMPER;
        }

        public static final class Default {
            public static final Integer PRIMARY_CONTROLLER_PORT = 0;
            public static final CommandXboxController PRIMARY_CONTROLLER = new CommandXboxController(PRIMARY_CONTROLLER_PORT);
            public static final Double JOYSTICK_X_DEADZONE = (5) * (Math.pow(10, -2));
            public static final Double JOYSTICK_Y_DEADZONE = (5) * (Math.pow(10, -2));
            public static final Double SPEED_COEFFICIENT_SENSITIVITY = (5) * (Math.pow(10, -2));
            public static final Trigger TRIGGER_INCREMENT = PRIMARY_CONTROLLER.povLeft();
            public static final Trigger TRIGGER_DECREMENT = PRIMARY_CONTROLLER.povRight();
            public static final Trigger TRIGGER_INTAKE_OUT = PRIMARY_CONTROLLER.leftTrigger();
            public static final Trigger TRIGGER_INTAKE_IN = PRIMARY_CONTROLLER.rightTrigger();
            public static final Trigger TRIGGER_INTAKE_STOP = PRIMARY_CONTROLLER.x();
            public static final Trigger TRIGGER_FIRE_OBJECT = PRIMARY_CONTROLLER.a();
            public static final Trigger TRIGGER_MODE_SWITCH = PRIMARY_CONTROLLER.b();
            public static final Trigger TRIGGER_DB_MODE = PRIMARY_CONTROLLER.y();
            public static final Trigger TRIGGER_L_ARM_CONTROL = PRIMARY_CONTROLLER.leftBumper();
            public static final Trigger TRIGGER_R_ARM_CONTROL = PRIMARY_CONTROLLER.rightBumper();
        }
    }

    /**
     * Don't ask how this works, or why I wrote it.
     * 
     * @author Cody Washington
     */
    public static final class Functions {
        /**
         * retrieves a method from a class and executes it regardless of the method's
         * protection level
         * 
         * @param Instance_Any  - Object to invoke on
         * @param MethodName    - Name of Method
         * @param Arguments_Any - Arguments of Method
         * @return the result of the Invoked Method
         */
        public static Object getMethodAndExecute(Object Instance_Any, String MethodName, Object... Arguments_Any) {
            var ArgumentTypes = Arrays.stream(Arguments_Any).map(Object::getClass).toArray(Class<?>[]::new);
            if (ArgumentTypes.length > 0) {
                try {
                    var MethodCapture = Instance_Any.getClass().getDeclaredMethod(MethodName, ArgumentTypes);
                    MethodCapture.setAccessible(true);
                    var MethodReturn = MethodCapture.invoke(Instance_Any.getClass(), Arguments_Any);
                    return (MethodReturn == (null)) ? Void.class : MethodReturn;
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
                    exception.printStackTrace();
                    return null;
                }
            } else {
                try {
                    var MethodCapture = Instance_Any.getClass().getDeclaredMethod(MethodName);
                    MethodCapture.setAccessible(true);
                    var MethodReturn = MethodCapture.invoke(Instance_Any);
                    return (MethodReturn == (null)) ? Void.class : MethodReturn;
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
                    exception.printStackTrace();
                    return null;
                }
            }
        }

        /**
         * retrieves a method from a class and executes it regardless of the method's
         * protection level
         * 
         * @param Class_Any - Class of Field
         * @param FieldName - Name of Field
         * @return the value of the Field
         */
        public static Object getFieldValue(Class<?> Class_Any, String FieldName) {
            try {
                return (Class_Any.getDeclaredField(FieldName).get(Class_Any));
            } catch (NullPointerException | SecurityException | NoSuchFieldException
                    | IllegalAccessException exception) {
                exception.printStackTrace();
                return null;
            }
        }

        public static Double norm(Double a, Double b) {
            return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        }
    }
}