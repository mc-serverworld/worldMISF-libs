/*
 *
 *  * WorldMISF-lib: library and basic component of mc-serverworld
 *  * Copyright (C) 2020-2021 mc-serverworld
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Setter;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

public class SSLSocketKey {
    private SSLContext ctx;
    private KeyManagerFactory kmf;
    private TrustManagerFactory tmf;
    private KeyStore keyStore;
    private KeyStore trustStore;

    @Setter(AccessLevel.PUBLIC) private String KEY_STORE_FILE;
    @Setter(AccessLevel.PUBLIC) private String TRUST_KEY_STORE_FILE;
    @Setter(AccessLevel.PUBLIC) private String KEY_STORE_PASSWORD;
    @Setter(AccessLevel.PUBLIC) private String TRUST_KEY_STORE_PASSWORD;

    public SSLContext getCtx(){
        try {
            ctx = SSLContext.getInstance("TLSv1.2");

            kmf = KeyManagerFactory.getInstance("SunX509");
            tmf = TrustManagerFactory.getInstance("SunX509");

            keyStore = KeyStore.getInstance("PKIX");
            trustStore = KeyStore.getInstance("PKIX");

            keyStore.load(new FileInputStream(KEY_STORE_FILE), KEY_STORE_PASSWORD.toCharArray());
            trustStore.load(new FileInputStream(TRUST_KEY_STORE_FILE), TRUST_KEY_STORE_PASSWORD.toCharArray());

            kmf.init(keyStore, KEY_STORE_PASSWORD.toCharArray());
            tmf.init(trustStore);

            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            return ctx;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
