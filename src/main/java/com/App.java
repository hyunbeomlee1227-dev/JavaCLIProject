package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    int lastID = 0;
    List<WiseSaying> wiseSayings = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");

                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            }
        }
    }
    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = new WiseSaying(++lastID, content, author);

        wiseSayings.add(wiseSaying);
    }

    void actionList() {
        System.out.println("번호/ 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; --i) {
            WiseSaying wiseSaying = wiseSayings.get(i);

            if (wiseSaying == null) {
                continue;
            }

            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    void actionDelete(String cmd) {
        String[] cmdBits = cmd.split("=");

        if(cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("ID 확인해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        wiseSayings.removeIf(wise -> wise.getId() == id);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }
}

