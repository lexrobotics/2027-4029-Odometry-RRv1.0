package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "BaseTankTeleOp", group = "TankDrive")
public class BaseTankTeleOp extends  LinearOpMode{

    private DcMotorEx leftBack, rightBack, leftFront, rightFront;
    private double motorPower = 0.25; //%max valocity

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
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Its the Base Tank Drive TeleOp!", "Initialized! Lets do this!");

        waitForStart();

        while(opModeIsActive()){
            double leftStickX = gamepad1.left_stick_x;
            double leftStickY = gamepad1.left_stick_y; // Reversed because the y-stick has up as negative y-values and down as positive y-values
            double rightStickX = gamepad1.right_stick_x;
            double rightStickY = gamepad1.right_stick_y; // Reversed because the y-stick has up as negative y-values and down as positive y-values

            // Driving vertically forward
            if(leftStickY > 0.5){
                leftFront.setPower(motorPower);
                leftBack.setPower(motorPower);
                rightFront.setPower(motorPower);
                rightBack.setPower(motorPower);

            // Driving vertically backward
            } else if(leftStickY < -0.5){
                leftFront.setPower(-motorPower);
                leftBack.setPower(-motorPower);
                rightFront.setPower(-motorPower);
                rightBack.setPower(-motorPower);

            // Driving horizontally to the right
            } else if(rightStickX > 0.5){ // Turing
                leftFront.setPower(-motorPower);
                leftBack.setPower(-motorPower);
                rightFront.setPower(motorPower);
                rightBack.setPower(motorPower);

            // Driving horizontally to the left
            } else if(rightStickX < -0.5){
                leftFront.setPower(motorPower);
                leftBack.setPower(motorPower);
                rightFront.setPower(-motorPower);
                rightBack.setPower(-motorPower);

            // to put the robot at rest so it doesn't slip around like an idiot
            } else {
                leftFront.setPower(0);
                leftBack.setPower(0);
                rightFront.setPower(0);
                rightBack.setPower(0);
            }

            telemetry.addData("leftFront", leftFront);
            telemetry.addData("\nleftBack", leftBack);
            telemetry.addData("\nrightFront", rightFront);
            telemetry.addData("\nrightBack", rightBack);// Finish telemetry
        }
    }
}