// ============================================================
// ECLIPSE ENGINE - Monitor de Performance
// ============================================================
// COMO USAR NO SKETCHWARE:
// 1. Adicione um Timer chamado "timer_perf" com intervalo 3000
// 2. No evento do Timer, cole este codigo
// 3. No onCreate, adicione: timer_perf.start();
//
// NOTA: 3 segundos e suficiente para monitores.
//       Nunca use menos que 2000ms para evitar lag.
// ============================================================

float densidade = getResources().getDisplayMetrics().density;
String corDestaque = "#00E5FF";
String corAmbar = "#FFB300";
String corVermelho = "#FF1744";

// --- RAM ---
try {
    android.app.ActivityManager am = (android.app.ActivityManager)
        getSystemService(android.content.Context.ACTIVITY_SERVICE);
    android.app.ActivityManager.MemoryInfo mi =
        new android.app.ActivityManager.MemoryInfo();
    am.getMemoryInfo(mi);
    int ramPercent = (int)(((mi.totalMem - mi.availMem) * 100)
        / mi.totalMem);

    int largTrilha = barra_ramTrilha.getWidth();
    if (largTrilha > 0) {
        int largPreench = (int)(largTrilha * (ramPercent / 100.0));
        android.widget.LinearLayout.LayoutParams p =
            (android.widget.LinearLayout.LayoutParams)
            barra_ramPreench.getLayoutParams();
        p.width = largPreench;
        barra_ramPreench.setLayoutParams(p);

        // Cor dinamica por nivel
        String cor = corDestaque;
        if (ramPercent > 80) cor = corVermelho;
        else if (ramPercent > 50) cor = corAmbar;

        android.graphics.drawable.GradientDrawable fb =
            new android.graphics.drawable.GradientDrawable();
        fb.setColor(Color.parseColor(cor));
        fb.setCornerRadius((int)(3 * densidade));
        barra_ramPreench.setBackground(fb);
    }
    txt_ramLabel.setText("RAM  " + ramPercent + "%");
} catch (Exception e) {}

// --- ARMAZENAMENTO ---
try {
    android.os.StatFs stat = new android.os.StatFs(
        android.os.Environment.getDataDirectory().getPath()
    );
    long total = stat.getTotalBytes();
    long livre = stat.getAvailableBytes();
    int stPercent = (int)(((total - livre) * 100) / total);

    int largTrilha2 = barra_storageTrilha.getWidth();
    if (largTrilha2 > 0) {
        int largPreench2 = (int)(largTrilha2 * (stPercent / 100.0));
        android.widget.LinearLayout.LayoutParams p2 =
            (android.widget.LinearLayout.LayoutParams)
            barra_storagePreench.getLayoutParams();
        p2.width = largPreench2;
        barra_storagePreench.setLayoutParams(p2);

        String cor2 = corDestaque;
        if (stPercent > 80) cor2 = corVermelho;
        else if (stPercent > 50) cor2 = corAmbar;

        android.graphics.drawable.GradientDrawable fb2 =
            new android.graphics.drawable.GradientDrawable();
        fb2.setColor(Color.parseColor(cor2));
        fb2.setCornerRadius((int)(3 * densidade));
        barra_storagePreench.setBackground(fb2);
    }
    long usadoGB = (total - livre) / (1024 * 1024 * 1024);
    long totalGB = total / (1024 * 1024 * 1024);
    txt_storageLabel.setText("STORAGE  " + usadoGB + "/" + totalGB + " GB");
} catch (Exception e) {}

// --- CPU (indicador simplificado) ---
try {
    int cores = Runtime.getRuntime().availableProcessors();
    long maxMem = Runtime.getRuntime().maxMemory() / (1024 * 1024);
    long usedMem = (Runtime.getRuntime().totalMemory()
        - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
    int appPercent = (int)((usedMem * 100) / maxMem);

    int largTrilha3 = barra_cpuTrilha.getWidth();
    if (largTrilha3 > 0) {
        int largPreench3 = (int)(largTrilha3 * (appPercent / 100.0));
        android.widget.LinearLayout.LayoutParams p3 =
            (android.widget.LinearLayout.LayoutParams)
            barra_cpuPreench.getLayoutParams();
        p3.width = largPreench3;
        barra_cpuPreench.setLayoutParams(p3);

        String cor3 = corDestaque;
        if (appPercent > 80) cor3 = corVermelho;
        else if (appPercent > 50) cor3 = corAmbar;

        android.graphics.drawable.GradientDrawable fb3 =
            new android.graphics.drawable.GradientDrawable();
        fb3.setColor(Color.parseColor(cor3));
        fb3.setCornerRadius((int)(3 * densidade));
        barra_cpuPreench.setBackground(fb3);
    }
    txt_cpuLabel.setText("CPU  " + cores + " cores");
} catch (Exception e) {}
