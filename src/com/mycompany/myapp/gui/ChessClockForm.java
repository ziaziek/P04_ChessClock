/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.mycompany.myapp.Game;
import com.mycompany.myapp.connections.WebServiceConnection;
import com.mycompany.myapp.connections.domain.AbstractRequest;
import com.mycompany.myapp.connections.domain.AbstractResponse;

/**
 *
 * @author Przemo
 */
public abstract class ChessClockForm extends Form {

    protected abstract void initForm();

    protected abstract void updateGameFromResponse(final AbstractResponse resp);

    protected void makeConnectionWithGameUpdate(final AbstractRequest req, final Game game, final AbstractResponse response) throws Exception {
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        WebServiceConnection con;
        con = new WebServiceConnection(req.getRequest());
        con.getConnectionRequest().setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(con.getConnectionRequest());
            try {
                response.extractResultsFromBody(con.getResponseBody());
                updateGameFromResponse(response);
            } catch (SecurityException | IllegalArgumentException ex) {
                //Logger.getLogger(ChessClockForm.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
