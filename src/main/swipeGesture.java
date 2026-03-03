// ============================================================
// ECLIPSE ENGINE - Gesto de Swipe
// ============================================================
// COMO USAR NO SKETCHWARE:
// 1. Crie duas variaveis Number no Sketchware:
//    - "touchInicioY" (valor inicial: 0)
//    - "touchFimY" (valor inicial: 0)
// 2. Va em MainActivity > Eventos > onTouch do linear_principal
// 3. Cole este codigo no bloco "add source directly"
// ============================================================

int acao = _motionEvent.getAction();

// Dedo tocou a tela
if (acao == android.view.MotionEvent.ACTION_DOWN) {
    touchInicioY = _motionEvent.getY();
}

// Dedo saiu da tela
if (acao == android.view.MotionEvent.ACTION_UP) {
    touchFimY = _motionEvent.getY();
    float deltaY = touchInicioY - touchFimY;

    // SWIPE PARA CIMA = abrir gaveta de apps
    if (deltaY > 300) {
        android.content.Intent intent = new android.content.Intent();
        intent.setClass(getApplicationContext(), AppDrawerActivity.class);
        startActivity(intent);
        overridePendingTransition(
            android.R.anim.slide_in_left,
            android.R.anim.fade_out
        );
    }

    // SWIPE PARA BAIXO = abrir notificacoes do sistema
    if (deltaY < -300) {
        try {
            Object sbService = getSystemService("statusbar");
            Class<?> sbClass = Class.forName(
                "android.app.StatusBarManager");
            java.lang.reflect.Method expand =
                sbClass.getMethod("expandNotificationsPanel");
            expand.invoke(sbService);
        } catch (Exception e) {
            // Sem permissao, nao faz nada
        }
    }
}
