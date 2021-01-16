package de.cschillingtschuehly.gcwebx.modell;

public interface View {
    public static interface External {
    }
    public static interface Internal extends External {
    }
    public static interface Prohibited extends Internal{

    }

}