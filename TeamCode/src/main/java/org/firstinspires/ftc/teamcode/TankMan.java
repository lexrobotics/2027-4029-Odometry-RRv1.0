package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "TankMan)", group = "TankDrive")
public class TankMan extends  LinearOpMode{

    private DcMotor Lback, Lfront, Rback, Rfront;

    @Override
    public void runOpMode() throws  InterruptedException{

        Lback = hardwareMap.get(DcMotor.class, "LB_motor"); //left back motor
        Rback = hardwareMap.get(DcMotor.class, "RB_motor"); //right back motor
        Lfront = hardwareMap.get(DcMotor.class, "LF_motor"); //left front motor
        Rfront = hardwareMap.get(DcMotor.class, "RF_motor"); //right front motor

        telemetry.addData("Its the Road Runner Up!", "Initialized! Lets do this!");


        waitForStart();

        while(opModeIsActive()){

            Lback.setPower(.25);
            Rback.setPower(.25);
            Lfront.setPower(.25);
            Rfront.setPower(.25);


            telemetry.addData("\nThats crazy", "");

        }


    }



}
