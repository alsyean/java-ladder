package laddergame.domain.vo;

import java.util.Objects;

public class Column {
    private static final int MIN_COLUMN = 1;
    private final int column;

    public Column(final int column) {
        validateColumn(column);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    private void validateColumn(final int column) {
        if (column < MIN_COLUMN) {
            throw new IllegalArgumentException("열 번호는 1보다 작을 수 없습니다. - " + column);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column column1 = (Column) o;
        return getColumn() == column1.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn());
    }
}
