package org.example;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RingService {

    public List<Ring> getAllRings() {
        Map<String, Ring> rings = new LinkedHashMap<>();

        InputStream input = getClass()
                .getResourceAsStream("/data/rings.csv");

        if (input == null) {
            throw new IllegalStateException(
                    "rings.csv が見つかりません。"
            );
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        input,
                        StandardCharsets.UTF_8
                ))) {

            // ヘッダーを読み飛ばす
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] columns = line.split(",", -1);

                if (columns.length != 4) {
                    throw new IllegalArgumentException(
                            "rings.csv の列数が4列ではありません: "
                                    + line
                    );
                }

                String name = columns[0].trim();
                double weight = Double.parseDouble(
                        columns[1].trim()
                );
                String effectType = columns[2].trim();
                double effectValue = Double.parseDouble(
                        columns[3].trim()
                );

                Ring ring = rings.get(name);

                if (ring == null) {
                    ring = new Ring(
                            name,
                            weight,
                            new ArrayList<>()
                    );
                    rings.put(name, ring);
                }

                ring.getEffects().add(
                        new RingEffect(
                                effectType,
                                effectValue
                        )
                );
            }

        } catch (Exception e) {
            throw new IllegalStateException(
                    "rings.csv の読み込みに失敗しました。",
                    e
            );
        }

        return new ArrayList<>(rings.values());
    }
}
