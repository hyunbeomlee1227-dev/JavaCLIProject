package com.domain.wiseSaying;

import com.WiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private  final List<WiseSaying> wiseSayingList = new ArrayList<>();
    int lastId = 0;

    public List<WiseSaying> getWiseSayingList() {
        return wiseSayingList;
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayingList.remove(wiseSaying);
    }

    void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
        wiseSaying.setModifyDate();
    }

    WiseSaying findById(int id) {
        return wiseSayingList.stream()
                .filter(ws -> ws.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("해당 아이디는 존재하지 않습니다.");
                    return null;
                });
    }

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSaying.setCreateDate();
            wiseSaying.setModifyDate();
            wiseSayingList.add(wiseSaying);
        } else {
            wiseSaying.setModifyDate();
        }
    }
}