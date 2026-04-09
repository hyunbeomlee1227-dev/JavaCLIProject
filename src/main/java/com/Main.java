package com;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("명령)");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료"))
            {
                System.out.println("프로그램을 종료합니다.");

                break;
            }
        }
    }
}
