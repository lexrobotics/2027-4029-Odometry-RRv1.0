package org.firstinspires.ftc.teamcode.NonRRTesting;

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

@TeleOp(name = "TankMan)", group = "Test")
public class TankWheelTester extends  LinearOpMode{

    private DcMotor leftBack, rightBack, leftFront, rightFront;
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

        telemetry.addData("Its the Tank Drive Wheel Tester!", "Initialized! Lets do this!");

        waitForStart();

        while(opModeIsActive()){
            if (gamepad1.left_bumper) { // If left bumper is pressed reverse the motor power
                motorPower = -motorPower;
            }

            if (gamepad1.y) {
                leftFront.setPower(motorPower);
            } else {
                leftFront.setPower(0);
            }
            if (gamepad1.x) {
                leftBack.setPower(motorPower);
            } else {
                leftBack.setPower(0);
            }
            if (gamepad1.b) {
                rightFront.setPower(motorPower);
            } else {
                rightFront.setPower(0);
            }
            if (gamepad1.a) {
                rightBack.setPower(motorPower);
            } else {
                rightBack.setPower(0);
            }
        }
    }
}