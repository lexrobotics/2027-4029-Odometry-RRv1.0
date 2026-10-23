package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import java.util.List;
@TeleOp
public class LimeLight extends LinearOpMode {
    private Limelight3A lime;

    private int pipe = 3;
    //pipe 0 = blue
    //pipe 1 = yellow
    //pipe 2 = red
    //pipe 3 = aprilTags

//    public Limelight(int pipeline){
//        pipe = pipeline;
//    }

    @Override
    public void runOpMode() {

//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);


        lime = hardwareMap.get(Limelight3A.class, "lime");
        lime.setPollRateHz(30);
        lime.start();

        lime.pipelineSwitch(pipe); // blue pipeline

        telemetry.addData("Pipeline", pipe);
        telemetry.addLine("LimeLight Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            LLResult test = lime.getLatestResult();
            if (test != null && test.isValid()) {

                if (pipe == 0 || pipe == 1 || pipe == 2) {
                    telemetry.addLine("color detected");


                    double tx = test.getTx();
                    double ty = test.getTy();
                    double size = test.getTa();


                    telemetry.addData("Pipline", pipe);
                    telemetry.addData("Tx", "%.2f", tx);
                    telemetry.addData("Ty", "%.2f", ty);
                    telemetry.addData("Size", "%.2f",size);

                } else if (pipe == 3) {
                    List<LLResultTypes.FiducialResult> tags =
                            test.getFiducialResults();
                    telemetry.addData("aprilTags Dectected", tags.size()
                    );

                    for (int i = 0; i < tags.size(); i++) {

                        LLResultTypes.FiducialResult tag = tags.get(i);
                        telemetry.addLine(
                                "____________________"

                        );
                        telemetry.addData("tag" + i + " ID", tag.getFiducialId());

                        telemetry.addData("tag" + i + " X", "%.2f", tag.getTargetXDegrees());

                        telemetry.addData("tag" + i + "Y", "%.2f", tag.getTargetYDegrees());

                        telemetry.addData("tag" + i + "Area", "%.2f", tag.getTargetArea());

                        telemetry.addData("tag" + i + "Family", tag.getFamily());


                    }

                }

            } else {
                if (pipe == 3)

                    telemetry.addData("Apriltag", "No apriltag detected");
                else
                    telemetry.addData("Limelight", "No target detected");

            }

            telemetry.update();
            sleep(30);
        }

        lime.stop();
    }
}
