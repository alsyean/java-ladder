package ladder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LadderTest {

    @Test
    void testRunWhenNoLine() {
        Ladder ladder = new Ladder(1, 3);
        int target = ladder.run(0);
        assertThat(target).isEqualTo(0);

        target = ladder.run(2);
        assertThat(target).isEqualTo(2);

    }

    @Test
    void testRunWhenLineLeft() {
        // 0 1 1
        Ladder ladder = new Ladder(1, 3);
        ladder.drawLine(0, 1);

        int target = ladder.run(2);
        assertThat(target).isEqualTo(1);

        // 1 1 0
        ladder = new Ladder(1, 3);
        ladder.drawLine(0, 0);

        target = ladder.run(0);
        assertThat(target).isEqualTo(1);
    }

    @Test
    void testRunWhenLineRight() {
        // 0 1 1
        Ladder ladder = new Ladder(1, 3);
        ladder.drawLine(0, 1);

        int target = ladder.run(1);
        assertThat(target).isEqualTo(2);

        ladder = new Ladder(1, 3);
        ladder.drawLine(0, 1);

        target = ladder.run(2);
        assertThat(target).isEqualTo(1);
    }

    @Test
    void testRunWhenMultiRows() {
        // 1 1 0 0
        // 0 1 1 0
        // 0 0 1 1

        Ladder ladder = new Ladder(3, 4);
        ladder.drawLine(0, 0);
        ladder.drawLine(1, 1);
        ladder.drawLine(2, 2);

        assertThat(ladder.run(0)).isEqualTo(3);
        assertThat(ladder.run(1)).isEqualTo(0);
        assertThat(ladder.run(2)).isEqualTo(1);
        assertThat(ladder.run(3)).isEqualTo(2);

    }
}
