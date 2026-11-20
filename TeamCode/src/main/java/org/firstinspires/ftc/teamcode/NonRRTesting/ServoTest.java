package org.firstinspires.ftc.teamcode.NonRRTesting;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTest extends LinearOpMode{

    Servo servo;
    double position = 0.0;
    // start = 0.179, end
    double amount = 0.0005;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "transfer");
        servo.setPosition(0.0);


        waitForStart();
        while(opModeIsActive()) {
            if (position < 1.0 && gamepad2.right_bumper) {
                position += amount;
            } else if (position > 0.0 && gamepad2.left_bumper) {
                position -= amount;
            }


            servo.setPosition(position);
            telemetry.addData("Position", position);
            telemetry.update();
        }
    }
}
