package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "BaseTankTeleOp", group = "TankDrive")
public class BaseTankTeleOp extends  LinearOpMode{

    private DcMotor leftBack, rightBack, leftFront, rightFront;
    private double leftStickY = gamepad1.left_stick_y;
    private double leftStickX = gamepad1.left_stick_x;
    private double rightStickX = gamepad1.right_stick_x;
    private double rightStickY = gamepad1.right_stick_y;

    private double motorPower = 0.75;

    @Override
    public void runOpMode() throws  InterruptedException{
        // The config and variable names are set to match Roadrunner's defaults
        leftFront = hardwareMap.get(DcMotor.class, "leftFront"); // Left front motor
        leftBack = hardwareMap.get(DcMotor.class, "leftBack"); // Left back motor
        rightFront = hardwareMap.get(DcMotor.class, "rightFront"); // Right front motor
        rightBack = hardwareMap.get(DcMotor.class, "rightBack"); // Right back motor

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Its the Base Tank Drive TeleOp!", "Initialized! Lets do this!");

        waitForStart();

        while(opModeIsActive()){
            // Driving
            if(rightStickY > 0){
                leftFront.setPower(motorPower);
                leftBack.setPower(motorPower);
                rightFront.setPower(motorPower);
                rightBack.setPower(motorPower);
            } else if(rightStickY < 0){
                leftFront.setPower(-motorPower);
                leftBack.setPower(-motorPower);
                rightFront.setPower(-motorPower);
                rightBack.setPower(-motorPower);
            }

            // Turing
            if(rightStickX > 0){
                leftFront.setPower(motorPower);
                leftBack.setPower(motorPower);
                rightFront.setPower(-motorPower);
                rightBack.setPower(-motorPower);
            } else if(rightStickX < 0){
                leftFront.setPower(-motorPower);
                leftBack.setPower(-motorPower);
                rightFront.setPower(motorPower);
                rightBack.setPower(motorPower);
            }
        }
    }
}