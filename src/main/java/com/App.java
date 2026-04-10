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

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionDelete(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
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

    void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            return;
        }

        delete(wiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id < -1) {
            System.out.println("숫자를 입력해주세요.");
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

