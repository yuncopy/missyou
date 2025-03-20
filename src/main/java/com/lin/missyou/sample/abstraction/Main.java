package com.lin.missyou.sample.abstraction;

import com.lin.missyou.sample.abstraction.hero.Camille;
import com.lin.missyou.sample.abstraction.hero.Diana;
import com.lin.missyou.sample.abstraction.hero.Irelia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name = Main.getPlayerInput();
        ISkill  skill;
        switch (name){
            case "irelia":
                skill = new Irelia();
                break;
            case "diana":
                skill = new Diana();
                break;
            case "camille":
                skill = new Camille();
                break;
            default:
                throw new RuntimeException("Invalid Hero Name");
        }
        //单纯interface好处：统一方法调用 依赖抽象 但无法统一实例   依旧存在switch case
        skill.r();
    }

    private static String getPlayerInput(){
        System.out.println("Enter a Hero's Name");
        Scanner scanner= new Scanner(System.in);
        return scanner.nextLine();
    }
}
