package org.example;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArmorService {

    public List<Armor> getAllArmors() {
        List<Armor> armors = new ArrayList<>();

        InputStream input = getClass()
                .getResourceAsStream("/data/armor.csv");

        if (input == null) {
            throw new IllegalStateException(
                    "armor.csv が見つかりません。"
            );
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        input,
                        StandardCharsets.UTF_8
                ))) {

            String line;

            // 1行目のヘッダーを読み飛ばす
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] columns = line.split(",", -1);

                if (columns.length != 16) {
                    throw new IllegalArgumentException(
                            "armor.csv の列数が16列ではありません: "
                                    + line
                    );
                }

                Armor armor = new Armor(
                        columns[0].trim(),
                        columns[1].trim(),
                        Double.parseDouble(columns[2].trim()),
                        Double.parseDouble(columns[3].trim()),
                        Double.parseDouble(columns[4].trim()),
                        Double.parseDouble(columns[5].trim()),
                        Double.parseDouble(columns[6].trim()),
                        Double.parseDouble(columns[7].trim()),
                        Double.parseDouble(columns[8].trim()),
                        Double.parseDouble(columns[9].trim()),
                        Double.parseDouble(columns[10].trim()),
                        Double.parseDouble(columns[11].trim()),
                        Double.parseDouble(columns[12].trim()),
                        Double.parseDouble(columns[13].trim()),
                        Double.parseDouble(columns[14].trim()),
                        Double.parseDouble(columns[15].trim())
                );

                armors.add(armor);
            }

        } catch (Exception e) {
            throw new IllegalStateException(
                    "armor.csv の読み込みに失敗しました。",
                    e
            );
        }

        return armors;
    }
}
