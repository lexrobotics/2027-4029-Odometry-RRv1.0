package org.firstinspires.ftc.teamcode.TeleOp.Scrimage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "ScrimageTeleOp", group = "TankDrive")
public class ScrimageTeleOp extends  LinearOpMode{

    // Drivetrain
    private DcMotorEx leftBack, rightBack, leftFront, rightFront;
    private double motorVelocity = 2464.0/2;
    private boolean slowMode = false;

    //Mechanisms
    private DcMotor intake;
    private DcMotor outtake;
    private Servo transfer;

    private double outtakePercentVelocity = 0.5; // Change as needed
    private double intakePercentVelocity = 0.75;  // Change as needed
    private double transferPushPosition = 0.464; // Change as needed
    private double transferRestPosition = 0.7215; // Change as needed
    private double amount = 0.0005;
    private double position = transferRestPosition;

    @Override
    public void runOpMode() throws  InterruptedException {
        // Drivetrain
        // The config and variable names are set to match Roadrunner's defaults
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront"); // Left front motor
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack"); // Left back motor
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront"); // Right front motor
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack"); // Right back motor

        // Reverses the necessary wheel motors so that they move in the same direction (forward when positive power is given
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightBack.setDirection(DcMotorSimple.Direction.REVERSE);


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

        // Mechanisms
        outtake = hardwareMap.get(DcMotor.class, "outtake");
        intake = hardwareMap.get(DcMotor.class, "intake");
        transfer = hardwareMap.get(Servo.class, "transfer");

        //outtake.setDirection(DcMotorSimple.Direction.REVERSE);  // if applicable
        intake.setDirection(DcMotorSimple.Direction.REVERSE);   // if applicable
        //transfer.setDirection(Servo.Direction.REVERSE;          // if applicable

        outtake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        transfer.setPosition(transferRestPosition);

        telemetry.addLine("Initialized!");

        waitForStart();

        while(opModeIsActive()){

            // Drivetrain
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
                if(slowMode == true) {
                    slowMode = false;
                }
                else if(slowMode == false){
                    slowMode = true;
                }
            }

            if(gamepad1.right_trigger_pressed){
                slowMode = false;
            }
            if(slowMode == true){ // Slow mode
                leftFront.setVelocity(motorVelocity*leftPower/10);
                leftBack.setVelocity(motorVelocity*leftPower/10);

                rightFront.setVelocity(motorVelocity*rightPower/10);
                rightBack.setVelocity(motorVelocity*rightPower/10);
            }
            else if(slowMode == false){ // Fast mode
                leftFront.setVelocity(motorVelocity * leftPower);
                leftBack.setVelocity(motorVelocity * leftPower);

                rightFront.setVelocity(motorVelocity * rightPower);
                rightBack.setVelocity(motorVelocity * rightPower);
            }

            telemetry.addData("leftFront", leftFront);
            telemetry.addData("\nleftBack", leftBack);
            telemetry.addData("\nrightFront", rightFront);
            telemetry.addData("\nrightBack", rightBack); // Finish telemetry
            telemetry.update();

            // Mechanisms
            if(gamepad2.right_trigger > 0.5){
                outtake.setPower(1);
            } else {
                outtake.setPower(0);

            }

            if(gamepad2.left_trigger > 0.5){
                intake.setPower(1);
            } else {
                intake.setPower(0);
            }

            if(gamepad2.right_bumper){
                position += amount;
            } else if(gamepad2.left_bumper){
                position -= amount;
            }
            transfer.setPosition(position);
        }
    }
}