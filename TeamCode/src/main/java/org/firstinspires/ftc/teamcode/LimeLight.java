package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.*;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;


import java.util.List;

public class LimeLight {
    private Limelight3A lime;

    private int pipe = 3;
    //pipe 0 = blue
    //pipe 1 = yellow
    //pipe 2 = red
    //pipe 3 = aprilTags

//    public Limelight(int pipeline){
//        pipe = pipeline;
//    }

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public LimeLight(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }

    public void init() {
        lime = hardwareMap.get(Limelight3A.class, "lime");
        lime.setPollRateHz(30);
        lime.start();

        lime.pipelineSwitch(pipe); // blue pipeline

        telemetry.addData("Pipeline", pipe);
        telemetry.addLine("LimeLight Ready");
        telemetry.update();
    }

    public void loop(){
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

                int idealY = 69;
                int idealX = 420;

                while(ty > idealY+5 || ty < idealY-5) {
                    //move up or dor smth maybe
                    break;
                }
                while(tx > idealX+15 || tx < idealX-15) {
                    //move left  or right or  smth maybe
                    break;
                }

            }

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

            } else {
                if (pipe == 3)

                    telemetry.addData("Apriltag", "No apriltag detected");
                else
                    telemetry.addData("Limelight", "No target detected");

            }





            telemetry.update();

        }




        public void stop() {
            lime.stop();
        }

    }
