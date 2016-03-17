package net.fireimp.server.world;

public enum WorldSize {
    SMALL(8400, 2400),
    MEDIUM(12800, 3600),
    LARGE(16800, 4800);

    private final int width;
    private final int height;

    WorldSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileCount() {
        return width * height;
    }

    protected Tile[] newTileSet() {
        return new Tile[getTileCount()];
    }
}
