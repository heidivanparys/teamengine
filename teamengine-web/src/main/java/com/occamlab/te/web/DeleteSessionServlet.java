/****************************************************************************

 The Original Code is TEAM Engine.

 The Initial Developer of the Original Code is Northrop Grumman Corporation
 jointly with The National Technology Alliance.  Portions created by
 Northrop Grumman Corporation are Copyright (C) 2005-2006, Northrop
 Grumman Corporation. All Rights Reserved.

 Contributor(s): No additional contributors to date

 ****************************************************************************/
package com.occamlab.te.web;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.occamlab.te.util.Misc;

/**
 * Processes a request to delete an existing test session.
 * 
 */
public class DeleteSessionServlet extends HttpServlet {

    private static final long serialVersionUID = 7544788524756976408L;

    Config Conf;

    public void init() throws ServletException {
        Conf = new Config();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String sessionId = request.getParameter("session");
            File userdir = new File(Conf.getUsersDir(), request.getRemoteUser());
            File sessiondir = new File(userdir, sessionId);
            Misc.deleteDir(sessiondir);
            response.sendRedirect("sessionDeleted.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}