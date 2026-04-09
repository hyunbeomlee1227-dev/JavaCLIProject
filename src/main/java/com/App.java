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
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
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

        int id = CmdSplitId(cmd);

        if (id < 0) {
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            return;
        }

        delete(wiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    void actionModify(String cmd) {
        int id = CmdSplitId(cmd);

        if (id < 0) {
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);

        System.out.printf("%d번 명언이 수정 되었습니다.\n", id);
    }

    int CmdSplitId(String cmd) {
        String[] cmdBits = cmd.split("=");

        if (cmdBits.length < 2 ||  cmdBits[1].isEmpty()) {
            System.out.println("id를 입력해주세요.");
            return -1;
        }

        return Integer.parseInt(cmdBits[1]);
    }

    WiseSaying findById(int id) {
        WiseSaying wiseSaying = null;
        for (int i = wiseSayings.size() - 1; i >= 0; --i) {
            if (wiseSayings.get(i).getId() == id) {
                wiseSaying = wiseSayings.get(i);
            }
        }

        if (wiseSaying == null) {
            System.out.println("해당 아이디는 존재하지 않습니다.");
            return null;
        }

        return wiseSaying;
    }

    void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

}

