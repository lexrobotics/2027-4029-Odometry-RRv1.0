package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class LimeLightTest extends LinearOpMode {

    public LimeLight limeLight = new LimeLight(telemetry);

    @Override
    public void runOpMode() throws InterruptedException {
        limeLight.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            limeLight.loop();
            sleep(30);
        }



        limeLight.stop();
    }
}
