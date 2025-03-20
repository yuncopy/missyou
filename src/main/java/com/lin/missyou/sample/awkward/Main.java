package com.lin.missyou.sample.awkward;

import com.lin.missyou.sample.awkward.hero.Diana;
import com.lin.missyou.sample.awkward.hero.Irelia;
import com.lin.missyou.sample.awkward.hero.Camille;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name = Main.getPlayerInput();
        //每次新增英雄都会添加到switch中 影响到主体的代码逻辑
        switch (name){
            case "irelia":
                Irelia irelia = new Irelia();
                irelia.r();
                break;
            case "diana":
                Diana diana = new Diana();
                diana.r();
                break;
            case "camille":
                Camille camille = new Camille();
                camille.r();
                break;
            default:
                System.out.println("Invalid Hero Name");
        }
    }

    private static String getPlayerInput(){
        System.out.println("Enter a Hero's Name");
        Scanner scanner= new Scanner(System.in);
        return scanner.nextLine();
    }
}
