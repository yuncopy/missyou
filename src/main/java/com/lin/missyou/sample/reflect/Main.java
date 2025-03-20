package com.lin.missyou.sample.reflect;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        String name = Main.getPlayerInput();
        ISkill skill = HeroFactory.createHero(name);  // 将不稳定的代码隔离
        skill.r(); // 对应main方法实现OCP 方法里面的代码相对稳定了
    }

    private static String getPlayerInput(){
        System.out.println("Enter a Hero's Name");
        Scanner scanner= new Scanner(System.in);
        return scanner.nextLine();
    }
}
