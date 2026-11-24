package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "GTA drive", group = "TankDrive")
public class GTADrive extends  LinearOpMode{

    private DcMotorEx leftBack, rightBack, leftFront, rightFront;
    private double motorVelocity = 2464.0/2;
    private CRServo AmoolyaAwesomeServo;
    private boolean[] driveMode = {false, false};

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
                if(driveMode[0] == true) {
                    driveMode[0] = false;
                }
                else if(driveMode[0] == false){
                    driveMode[0] = true;
                }
            }

            if(gamepad1.right_trigger_pressed){
                driveMode[1]=false;
            }
            if(driveMode[0] == true && driveMode[1] == false){ // Slow mode
                leftFront.setVelocity(motorVelocity*leftPower/10);
                leftBack.setVelocity(motorVelocity*leftPower/10);

                rightFront.setVelocity(motorVelocity*rightPower/10);
                rightBack.setVelocity(motorVelocity*rightPower/10);
            }
            else if(driveMode[0] == false && driveMode[1] == false){ // Fast mode
                leftFront.setVelocity(motorVelocity * leftPower);
                leftBack.setVelocity(motorVelocity * leftPower);

                rightFront.setVelocity(motorVelocity * rightPower);
                rightBack.setVelocity(motorVelocity * rightPower);
            }
            else if(driveMode[1]){
                leftFront.setVelocity(motorVelocity*2 * leftPower);
                leftBack.setVelocity(motorVelocity*2 * leftPower);

                rightFront.setVelocity(motorVelocity*2 * rightPower);
                rightBack.setVelocity(motorVelocity*2 * rightPower);
            }

            telemetry.addData("leftFront", leftFront);
            telemetry.addData("\nleftBack", leftBack);
            telemetry.addData("\nrightFront", rightFront);
            telemetry.addData("\nrightBack", rightBack);// Finish telemetry
            telemetry.update();
        }
    }
}