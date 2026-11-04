
package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService {

    private final List<Origin> origins = List.of(
            new Origin("騎士", 9,
                    12, 10, 11, 15, 13, 12, 9, 9, 7),

            new Origin("傭兵", 8,
                    11, 12, 11, 10, 10, 16, 10, 8, 9),

            new Origin("戦士", 7,
                    14, 6, 12, 11, 16, 9, 8, 9, 11),

            new Origin("伝令", 9,
                    12, 10, 9, 12, 12, 11, 8, 13, 11),

            new Origin("盗人", 5,
                    10, 11, 10, 9, 9, 13, 10, 8, 14),

            new Origin("刺客", 10,
                    10, 14, 11, 10, 10, 14, 11, 9, 10),

            new Origin("魔術師", 6,
                    9, 16, 9, 7, 7, 12, 16, 7, 12),

            new Origin("呪術師", 8,
                    11, 12, 10, 8, 12, 9, 14, 14, 7),

            new Origin("聖職者", 7,
                    10, 14, 9, 7, 12, 8, 7, 16, 13),

            new Origin("持たざる者", 1,
                    10, 10, 10, 10, 10, 10, 10, 10, 10)
    );

    public List<Origin> getOrigins() {
        return origins;
    }
}
