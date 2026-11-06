package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.*;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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

    public LimeLight(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void init(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        lime = hardwareMap.get(Limelight3A.class, "lime");
        lime.setPollRateHz(30);
        lime.start();

        lime.pipelineSwitch(pipe);

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
                telemetry.addData("Size", "%.2f", size);

                // postive x - camera is to the right

                int idealY = 69;
                int idealX = 420;

                while (ty > idealY + 5 || ty < idealY - 5) {
                    //move up or dor smth maybe
                    break;
                }
                while (tx > idealX + 15 || tx < idealX - 15) {
                    //move left  or right or  smth maybe
                    break;

                }


            } else if (pipe == 3) {

                List<LLResultTypes.FiducialResult> tags =
                        test.getFiducialResults();
                telemetry.addData("aprilTags Dectected", tags.size());

                if (tags.isEmpty()) {
                    telemetry.addData("AprilTag", "No apriltag detected ");

                }

                for (int i = 0; i < tags.size(); i++) {

                    LLResultTypes.FiducialResult tag = tags.get(i);
                    telemetry.addLine(
                            "____________________");

                    telemetry.addData("tag" + i + " ID", tag.getFiducialId());

                    telemetry.addData("tag" + i + " X Degrees", "%.2f", tag.getTargetXDegrees());

                    telemetry.addData("tag" + i + "Y Degrees", "%.2f", tag.getTargetYDegrees());

                    telemetry.addData("tag" + i + "Area", "%.2f", tag.getTargetArea());

                    telemetry.addData("tag" + i + "Family", tag.getFamily());

                    Pose3D pose = tag.getTargetPoseCameraSpace();

                    if (pose == null) {

                        telemetry.addLine("3d position in null");
                    } else {

                        Position position = pose.getPosition();

                        Position inches = position.toUnit(DistanceUnit.INCH);

                        double cameraX = inches.x;
                        double cameraY = inches.y;
                        double cameraZ = inches.z;

                        telemetry.addData("CameraX", "%.2f", cameraX);
                        telemetry.addData("CameraY", "%.2f", cameraY);
                        telemetry.addData("CameraZ", "%.2f", cameraZ);



                    }

                }

                }


            } else if (pipe == 3) {

                telemetry.addData("Apriltag", "No apriltag detected");

            } else {
                telemetry.addData("Limelight", "No target detected");

            }


            telemetry.update();

        }


        public void stop() {
            lime.stop();
        }



    }


