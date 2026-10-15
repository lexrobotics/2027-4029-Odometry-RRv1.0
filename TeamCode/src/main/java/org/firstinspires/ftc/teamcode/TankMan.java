package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//C:\Users\[YOUR LAPTOP USERNAME]\[Folder u put the sdk file in]\platform-tools-latest-windows\platform-tools\adb connect 192.168.43.1:5555
//EXAMPLE;   C:\Users\Super\Downloads\platform-tools-latest-windows\platform-tools\adb connect 192.168.43.1:5555
//use this to connect to the robot wirelessly
//
//step 1.
//connect to the control hub's wifi
//
//step 2.
//copy-paste the crazy string above into your andriod studio terminal, see bottom left of screen man
//
//step 3.
//        if it says "connect to 192.168.43.1:5555" then u did it correctly
@TeleOp(name = "TankMan)", group = "TankDrive")
public class TankMan extends  LinearOpMode{

    private DcMotor Lback, Lfront, Rback, Rfront;

    @Override
    public void runOpMode() throws  InterruptedException{

        Lback = hardwareMap.get(DcMotor.class, "LB_motor"); //left back motor
        Rback = hardwareMap.get(DcMotor.class, "RB_motor"); //right back motor
        Lfront = hardwareMap.get(DcMotor.class, "LF_motor"); //left front motor
        Rfront = hardwareMap.get(DcMotor.class, "RF_motor"); //right front motor

        Rback.setDirection(DcMotorSimple.Direction.REVERSE);
        Rfront.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Its the Road Runner Up!", "Initialized! Lets do this!");

        boolean joystickControls = false;

        waitForStart();

        while(opModeIsActive()){

            double motorPower = 0.75;
            double leftStickY = gamepad1.left_stick_y;
            double leftStickX = gamepad1.left_stick_x;

                if(gamepad1.right_bumper){ // Switch between using and not joystick controls once we press the right bumper
                    if(joystickControls){
                        joystickControls = false;
                    } else {
                        joystickControls = true;
                    }
                }

                if(joystickControls) {
                    // Change everything before the "else" - right now its jsut a copy  of what's after the "else"
                    if (gamepad1.left_bumper) {
                        motorPower = -motorPower;
                    }
                    Lback.setPower(leftStickY);
                    Rback.setPower(leftStickY);
                    Lfront.setPower(leftStickX);
                    Rfront.setPower(leftStickX);

                }
                else {
                    if (gamepad1.left_bumper) {
                        motorPower = -motorPower;
                    }

                    if (gamepad1.a) {
                        Lback.setPower(motorPower);
                    } else {
                        Lback.setPower(0);
                    }
                    if (gamepad1.b) {
                        Lfront.setPower(motorPower);
                    } else {
                        Lfront.setPower(0);
                    }
                    if (gamepad1.x) {
                        Rfront.setPower(motorPower);
                    } else {
                        Rfront.setPower(0);
                    }
                    if (gamepad1.y) {
                        Rback.setPower(motorPower);
                    } else {
                        Rback.setPower(0);
                    }
                }


            telemetry.addData("\nThats crazy", "");

        }


    }



}
