java.util.Calendar cal = java.util.Calendar.getInstance();
int hora = cal.get(java.util.Calendar.HOUR_OF_DAY);
int minuto = cal.get(java.util.Calendar.MINUTE);

// So atualiza quando o minuto muda (economia de CPU)
if (minuto != ultimoMinuto) {
    ultimoMinuto = minuto;

    // --- FORMATO DA HORA ---
    SharedPreferences sp = getSharedPreferences("eclipse_prefs", MODE_PRIVATE);
    boolean formato24h = sp.getBoolean("formato24h", true);

    String horaStr;
    if (formato24h) {
        horaStr = String.format("%02d:%02d", hora, minuto);
    } else {
        int hora12 = hora % 12;
        if (hora12 == 0) hora12 = 12;
        String ampm = hora > 11 ? "PM" : "AM";
        horaStr = String.format("%d:%02d %s", hora12, minuto, ampm);
    }
    txt_hora.setText(horaStr);

    // --- FORMATO DA DATA (portugues) ---
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
        "EEE, dd MMM", new java.util.Locale("pt", "BR")
    );
    txt_data.setText(sdf.format(cal.getTime()).toUpperCase());
}

// --- ATUALIZAR BATERIA ---
try {
    android.content.IntentFilter ifilter = new android.content.IntentFilter(
        android.content.Intent.ACTION_BATTERY_CHANGED
    );
    android.content.Intent batteryStatus = registerReceiver(null, ifilter);
    int level = batteryStatus.getIntExtra(
        android.os.BatteryManager.EXTRA_LEVEL, -1);
    int scale = batteryStatus.getIntExtra(
        android.os.BatteryManager.EXTRA_SCALE, -1);
    int batPercent = (int)((level / (float)scale) * 100);
    int status = batteryStatus.getIntExtra(
        android.os.BatteryManager.EXTRA_STATUS, -1);
    boolean carregando =
        status == android.os.BatteryManager.BATTERY_STATUS_CHARGING;

    String batIcon = carregando ? "⚡" : "🔋";
    txt_bateria.setText(batIcon + " " + batPercent + "%");

    // Cor muda conforme nivel
    if (batPercent <= 15) {
        txt_bateria.setTextColor(Color.parseColor("#FF1744"));
    } else if (batPercent <= 30) {
        txt_bateria.setTextColor(Color.parseColor("#FFB300"));
    } else {
        txt_bateria.setTextColor(Color.parseColor("#6C6C80"));
    }
} catch (Exception e) {
    txt_bateria.setText("🔋 --");
}

// --- ATUALIZAR RAM ---
try {
    android.app.ActivityManager am = (android.app.ActivityManager)
        getSystemService(android.content.Context.ACTIVITY_SERVICE);
    android.app.ActivityManager.MemoryInfo mi =
        new android.app.ActivityManager.MemoryInfo();
    am.getMemoryInfo(mi);
    long totalMB = mi.totalMem / (1024 * 1024);
    long usadaMB = (mi.totalMem - mi.availMem) / (1024 * 1024);
    txt_ram.setText("💾 " + usadaMB + "/" + totalMB + " MB");
} catch (Exception e) {
    txt_ram.setText("💾 --");
      }
