package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "GTA drive", group = "TankDrive")
public class GTADrive extends  LinearOpMode{

    private DcMotorEx leftBack, rightBack, leftFront, rightFront;
    private double motorVelocity = 2464.0;
    private boolean[] precisionDrive = {false};

    @Override
    public void runOpMode() throws  InterruptedException{
        // The config and variable names are set to match Roadrunner's defaults
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront"); // Left front motor
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack"); // Left back motor
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront"); // Right front motor
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack"); // Right back motor

        // Reverses the necessary wheel motors so that they move in the same direction (forward when positive power is given)
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        // Sets all the wheel motors to stop moving immediately instead of gently slowing down when the power is 0
        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        //
        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Its time to drive like its GTA 5!!", "As mlg said, 'It's driving time.'");

        waitForStart();

        while(opModeIsActive()){
            double leftStickX = -gamepad1.left_stick_x;
            double leftStickY = gamepad1.left_stick_y; // Reversed because the y-stick has up as negative y-values and down as positive y-values
            // double rightStickX = gamepad1.right_stick_x;
            // double rightStickY = gamepad1.right_stick_y; // Reversed because the y-stick has up as negative y-values and down as positive y-values

            double leftPower = leftStickY + leftStickX;
            double rightPower = leftStickY - leftStickX;

            double biggerPower = Math.max(Math.abs(leftPower), Math.abs(rightPower));

            if(biggerPower > 1.0){
                leftPower /= biggerPower;
                rightPower /= biggerPower;
            }

            if(gamepad1.left_trigger_pressed) {
                precisionDrive[0] = false;
            }
            if(gamepad1.right_trigger_pressed){
                precisionDrive[0] = true;
            }

            if(precisionDrive[0] == true){
                leftFront.setVelocity(motorVelocity*leftPower/10);
                leftBack.setVelocity(motorVelocity*leftPower/10);

                rightFront.setPower(motorVelocity*rightPower/10);
                rightBack.setPower(motorVelocity*rightPower/10);
            }
            else if(precisionDrive[0] == false){
                leftFront.setVelocity(motorVelocity * leftPower);
                leftBack.setVelocity(motorVelocity * leftPower);

                rightFront.setPower(motorVelocity * rightPower);
                rightBack.setPower(motorVelocity * rightPower);
            }








            telemetry.addData("leftFront", leftFront);
            telemetry.addData("\nleftBack", leftBack);
            telemetry.addData("\nrightFront", rightFront);
            telemetry.addData("\nrightBack", rightBack);// Finish telemetry
        }
    }
}