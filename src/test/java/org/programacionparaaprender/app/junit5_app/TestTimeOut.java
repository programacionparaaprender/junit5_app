package org.programacionparaaprender.app.junit5_app;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Tag("timeout")
public class TestTimeOut {
    
    @Test
	@Tag("pruebas")
	@DisplayName("Probando timeOut!")
	void testTimeOutAssertions() throws InterruptedException{
		assertTimeout(Duration.ofSeconds(6), ()->{
            TimeUnit.SECONDS.sleep(5);
        });
	}
    
    @Test
	@Tag("pruebas")
	@DisplayName("Probando timeOut por sobrepasar cinco segundos!")
	@Timeout(5)
	void pruebaTimeOut() throws InterruptedException{
		TimeUnit.SECONDS.sleep(6);
	}

	@Test
	@Tag("pruebas")
	@DisplayName("Probando timeOut por sobrepasar cinco segundos 2!")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void pruebaTimeOut2() throws InterruptedException{
		TimeUnit.SECONDS.sleep(6);
	}
}
