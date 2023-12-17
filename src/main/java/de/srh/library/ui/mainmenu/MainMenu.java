package de.srh.library.ui.mainmenu;

import de.srh.library.dto.Global;
import de.srh.library.dto.UserDto;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.borrowreturn.BorrowReturn;
import de.srh.library.ui.browselibrary.BrowseLibrary;
import de.srh.library.ui.infossettings.InfosSettings;
import de.srh.library.ui.login.LoginWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
    private JLabel pageTitle;
    private JButton infosSettings;
    private JButton borrowReturn;
    private JButton browseLibraries;
    private JPanel mainMenuWindow;
    private long userId = Global.loggedInUserId;
    private static UserService userService;
    private JButton logOut;

    public MainMenu() {

        setAutoRequestFocus(false);
        setContentPane(mainMenuWindow);
        setTitle("Main Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening main menu window ...");
        browseLibraries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowseLibrary browseLibrary = new BrowseLibrary();
                browseLibrary.setVisible(true);
            }
        });
        borrowReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BorrowReturn borrowReturn = new BorrowReturn();
                borrowReturn.setVisible(true);
            }
        });
        infosSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InfosSettings infosSettings = new InfosSettings();
                userService = UserServiceImpl.createInstance();
                UserDto userDto = userService.getUserById(userId).getData();
                infosSettings.loadUserInformation(userDto);
            }
        });
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Global.userLogOut();
                dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });
    }

    //Testing only
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}
