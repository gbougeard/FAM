/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.jsf.bootstrap;

import org.fam.ejb.common.FamConstantes;
import org.fam.common.log.LogUtil;
import org.fam.ejb.model.*;
import org.fam.ejb.session.*;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.event.InitDataDone;
import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;
import org.primefaces.event.DragDropEvent;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

/**
 * @author mask_hot
 */

@Singleton
@Named
@ApplicationScoped
public class InitData {

    //
    private Integer progress = 0;
    private Boolean initDone = false;
    //
    @Inject
    Event<InitDataDone> event;
    // CLUBS
    @EJB
    FamClubFacade ejbClub;
    private final List<FamClub> listClub = new ArrayList<FamClub>();
    // TEAMS
    @EJB
    FamTeamFacade ejbTeam;
    private final List<FamTeam> listTeam = new ArrayList<FamTeam>();
    // ORGANIZATIONS
    @EJB
    FamOrganizationFacade ejbOrganization;
    private final List<FamOrganization> listOrganization = new ArrayList<FamOrganization>();
    // SEASONS
    @EJB
    FamSeasonFacade ejbSeason;
    private final List<FamSeason> listSeason = new ArrayList<FamSeason>();
    // TYP_EVENT
    @EJB
    FamTypEventFacade ejbTypEvent;
    private final List<FamTypEvent> listTypEvent = new ArrayList<FamTypEvent>();
    // TYP_CARD
    @EJB
    FamTypCardFacade ejbTypCard;
    private final List<FamTypCard> listFamTypCard = new ArrayList<FamTypCard>();
    // SCALE
    @EJB
    FamScaleFacade ejbScale;
    private final List<FamScale> listScale = new ArrayList<FamScale>();
    // EVENT STATUS
    @EJB
    FamEventStatusFacade ejbEventStatus;
    private final List<FamEventStatus> listEventStatus = new ArrayList<FamEventStatus>();
    // TYP_PLACE
    @EJB
    FamTypPlaceFacade ejbTypPlace;
    private final List<FamTypPlace> listTypPlace = new ArrayList<FamTypPlace>();
    // TYP_COMPETITION
    @EJB
    FamTypCompetitionFacade ejbTypCompetition;
    private final List<FamTypCompetition> listTypCompetition = new ArrayList<FamTypCompetition>();
    // TYP_ABSENCE
    @EJB
    FamTypAbsenceFacade ejbTypAbsence;
    private final List<FamTypAbsence> listTypAbsence = new ArrayList<FamTypAbsence>();
    // TYP_ANSWER
    @EJB
    FamTypAnswerFacade ejbTypAnswer;
    private final List<FamTypAnswer> listTypAnswer = new ArrayList<FamTypAnswer>();
    // POSITION
    @EJB
    FamPositionFacade ejbPosition;
    private final List<FamPosition> listPosition = new ArrayList<FamPosition>();
    // TYP_MATCH
    @EJB
    FamTypMatchFacade ejbTypMatch;
    private final List<FamTypMatch> listTypMatch = new ArrayList<FamTypMatch>();
    // EVENT
    @EJB
    FamEventFacade ejbEvent;
    private final List<FamEvent> listEvent = new ArrayList<FamEvent>();
    // SEASON COMPETITION
    @EJB
    FamSeasonCompetitionFacade ejbSeasonCompetition;
    private final List<FamSeasonCompetition> listSeasonCompetition = new ArrayList<FamSeasonCompetition>();
    // USER
    @EJB
    FamUserFacade ejbUser;
    private final List<FamUser> listUser = new ArrayList<FamUser>();
    // PLAYER
    @EJB
    FamPlayerFacade ejbPlayer;
    private final List<FamPlayer> listPlayer = new ArrayList<FamPlayer>();
    // STAFF FUNCTION
    @EJB
    FamStaffFunctionFacade ejbStaffFunction;
    private final List<FamStaffFunction> listStaffFunction = new ArrayList<FamStaffFunction>();
    // EVENT
    @EJB
    FamWorkoutFacade ejbWorkout;
    private final List<FamWorkout> listWorkout = new ArrayList<FamWorkout>();
    // COUNTRY
    @EJB
    FamCountryFacade ejbCountry;
    // STATE
    @EJB
    FamStateFacade ejbState;
    // PROVINCE
    @EJB
    FamProvinceFacade ejbProvince;
    //CITY
    @EJB
    FamCityFacade ejbCity;

    public InitData() {
    }

    // 
    @PostConstruct
    void postConstruct() {
        LogUtil.log("IN: InitData:PostConstruct", Level.INFO, null);
        checkInitDone();
    }

    @Asynchronous
    public Future<Integer> asyncCreateData() {
        LogUtil.log("IN: createData", Level.INFO, null);
        progress = 0;
        //COUNTRY
        createCountries();
        logMsg("Countries");
        progress = 5;
        //STATE
        createStates();
        logMsg("States");
        progress = 6;
        //PROVINCE
        createProvinces();
        logMsg("Provinces");
        progress = 10;
        //CITY
        createCities();
        logMsg("Cities");
        progress++;
        // SEASONS
        createSeasons();
        logMsg("Seasons");
        progress++;
        // ORGANIZATIONS
        createOrganizations();
        logMsg("Organizations");
        progress++;
        // TYPMATCH
        createTypMatch();
        logMsg("TypMatch");
        progress++;
        // TYP_EVENT
        ejbTypEvent.initData();
        logMsg("TypEvent");
        progress++;
        // TYP_CARD
        ejbTypCard.initData();
        logMsg("TypCard");
        progress++;
        // SCALE
        ejbScale.initData();
        logMsg("Scale");
        progress++;
        // CLUBS
        createClubs();
        logMsg("Clubs");
        progress += 3;
        // TEAMS
        createTeams();
        logMsg("Teams");
        progress += 3;
        // EVENT_STATUS
        ejbEventStatus.initData();
        logMsg("EventStatus");
        progress++;
        // TYP_PLACE
        createTypPlace();
        logMsg("TypPlace");
        progress++;
        // COMPETITION
        createTypCompetition();
        logMsg("TypCompetition");
        progress++;
        // TYP_ABSENCE
        createTypAbsence();
        logMsg("TypAbsence");
        progress++;
        // TYP_ANSWER
        createTypAnswer();
        logMsg("TypAnswer");
        progress++;
        // POSITION
        createPosition();
        logMsg("Position");
        progress++;
        // EVENT
        createEvents();
        logMsg("Event");
        progress++;
        // WORKOUT
        createWorkout();
        logMsg("Workout");
        progress++;
        // SEASON COMPETITION
        createSeasonCompetition();
        logMsg("SeasonCompeition");
        progress++;
        // USER
        createUser();
        logMsg("User");
        progress = 90;
        // PLAYER
        createPlayer();
        logMsg("Player");
        progress = 100;
        // STAFF FUNCTION
//        createStaffFunction();
//        logMsg("StaffFunction");

        checkInitDone();

//        event.fire(new InitDataDone());
        return new AsyncResult<Integer>(progress);
    }

    public String createData() {
        Future<Integer> res = asyncCreateData();
        return null;
    }

    public void checkInitDone() {
        try {
            this.initDone = (ejbPlayer.findAll().size() > 0);
        } catch (Exception e) {
            String msg = "Cannot check InitDone via Table Player";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createClubs() {
        try {
            for (int i = 0; i < 10; i++) {
                FamClub club = new FamClub();
                club.setLibClub("Club_" + i);
                club.setCodeFff(i * 10);
                ejbClub.create(club);
                listClub.add(club);
            }
        } catch (Exception e) {
            LogUtil.log("create clubs failed", Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, "create clubs failed");
        }

    }

    private void createTeams() {
        try {
            for (FamClub club : listClub) {
                for (int i = 0; i <= 2; i++) {
                    FamTeam team = new FamTeam();
                    int num = club.getCodeFff() + i;
                    team.setLibTeam("Equipe_" + num);
                    team.setCodTeam("EQ" + num);
                    team.setFamClub(club);
                    ejbTeam.create(team);
                    listTeam.add(team);
                }
            }
        } catch (Exception e) {
            LogUtil.log("create teams failed", Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, "create clubs failed");
        }
    }

    private void createOrganizations() {
        try {
            FamOrganization org = new FamOrganization();
            org.setLibOrganization("Fédération Française de Football");
            org.setCodOrganization("FFF");
            ejbOrganization.create(org);
            listOrganization.add(org);

            org = new FamOrganization();
            org.setLibOrganization("Ligue Midi Pyrénées");
            org.setCodOrganization("LMP");
            org.setParent(listOrganization.get(0));
            ejbOrganization.create(org);
            listOrganization.add(org);

            org = new FamOrganization();
            org.setLibOrganization("District Midi Toulousain");
            org.setCodOrganization("DMT");
            org.setParent(listOrganization.get(1));
            ejbOrganization.create(org);
            listOrganization.add(org);

            org = new FamOrganization();
            org.setLibOrganization("UFOLEP");
            org.setCodOrganization("UFO");
            ejbOrganization.create(org);
            listOrganization.add(org);
        } catch (Exception e) {
            String msg = "create organization failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createSeasons() {
        try {
            DateTime dt = new DateTime();
            int year = dt.getYear();
            for (int i = 0; i < 5; i++) {
                FamSeason s = new FamSeason();
                String lib = String.format("%d/%d", year - i, year - i + 1);
                s.setLibSeason(lib);
                s.setCurrentSeason(i == 0);

                ejbSeason.create(s);
                listSeason.add(s);
            }
        } catch (Exception e) {
            String msg = "create seasons failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createTypPlace() {
        try {
            FamTypPlace item = new FamTypPlace();
            item.setLibTypPlace("Stabilisé");
            item.setCodTypPlace("Stabi");
            ejbTypPlace.create(item);
            listTypPlace.add(item);

            item = new FamTypPlace();
            item.setLibTypPlace("Synthétique");
            item.setCodTypPlace("Synth");
            ejbTypPlace.create(item);
            listTypPlace.add(item);

            item = new FamTypPlace();
            item.setLibTypPlace("Herbe");
            item.setCodTypPlace("Herbe");
            ejbTypPlace.create(item);
            listTypPlace.add(item);

            item = new FamTypPlace();
            item.setLibTypPlace("Autre");
            item.setCodTypPlace("Autre");
            ejbTypPlace.create(item);
            listTypPlace.add(item);
        } catch (Exception e) {
            String msg = "create typPlace failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createTypCompetition() {
        try {
            FamTypCompetition item = new FamTypCompetition();
            item.setLibTypCompetition("Division Honneur");
            item.setCodTypCompetition("DH");
            item.setIsChampionship(true);
            item.setFamTypMatch(listTypMatch.get(0));
            ejbTypCompetition.create(item);
            listTypCompetition.add(item);

            item = new FamTypCompetition();
            item.setLibTypCompetition("Promotion Honneur");
            item.setCodTypCompetition("PH");
            item.setIsChampionship(true);
            item.setFamTypMatch(listTypMatch.get(0));
            ejbTypCompetition.create(item);
            listTypCompetition.add(item);

            item = new FamTypCompetition();
            item.setLibTypCompetition("Excellence");
            item.setCodTypCompetition("EXL");
            item.setIsChampionship(true);
            item.setFamTypMatch(listTypMatch.get(2));
            ejbTypCompetition.create(item);
            listTypCompetition.add(item);

            item = new FamTypCompetition();
            item.setLibTypCompetition("1ere Division");
            item.setCodTypCompetition("1DIV");
            item.setIsChampionship(true);
            item.setFamTypMatch(listTypMatch.get(2));
            ejbTypCompetition.create(item);
            listTypCompetition.add(item);

            item = new FamTypCompetition();
            item.setLibTypCompetition("Coupe de France");
            item.setCodTypCompetition("CF");
            item.setIsChampionship(false);
            item.setFamTypMatch(listTypMatch.get(1));
            ejbTypCompetition.create(item);
            listTypCompetition.add(item);

            item = new FamTypCompetition();
            item.setLibTypCompetition("Match amical");
            item.setCodTypCompetition("A");
            item.setIsChampionship(false);
            item.setFamTypMatch(listTypMatch.get(0));
            ejbTypCompetition.create(item);
            listTypCompetition.add(item);
        } catch (Exception e) {
            String msg = "create typCompetition failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createTypAbsence() {
        try {
            FamTypAbsence item = new FamTypAbsence();
            item.setLibTypAbsence("Blessure");
            item.setCodTypAbsence("B");
            ejbTypAbsence.create(item);
            listTypAbsence.add(item);

            item = new FamTypAbsence();
            item.setLibTypAbsence("Vacances");
            item.setCodTypAbsence("V");
            ejbTypAbsence.create(item);
            listTypAbsence.add(item);
        } catch (Exception e) {
            String msg = "create typAbsence failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createTypAnswer() {
        try {
            FamTypAnswer item = new FamTypAnswer();
            item.setLibTypAnswer("Absent");
            item.setCodTypAnswer("A");
            item.setGrpTypAnswer(FamConstantes.GRP_ANSWER_NO);
            ejbTypAnswer.create(item);
            listTypAnswer.add(item);

            item = new FamTypAnswer();
            item.setLibTypAnswer("Présent");
            item.setCodTypAnswer("P");
            item.setGrpTypAnswer(FamConstantes.GRP_ANSWER_YES);
            ejbTypAnswer.create(item);
            listTypAnswer.add(item);

            item = new FamTypAnswer();
            item.setLibTypAnswer("Ne sait pas");
            item.setCodTypAnswer("N");
            item.setGrpTypAnswer(FamConstantes.GRP_ANSWER_MAYBE);
            ejbTypAnswer.create(item);
            listTypAnswer.add(item);
        } catch (Exception e) {
            String msg = "create typAnswer failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createPosition() {
        try {
            FamPosition item = new FamPosition();
            item.setLibPosition("Gardien de but");
            item.setCodPosition("G");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Latéral gauche");
            item.setCodPosition("LG");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Latéral droit");
            item.setCodPosition("LD");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Stoppeur");
            item.setCodPosition("ST");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Défenseur central");
            item.setCodPosition("DC");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Libéro");
            item.setCodPosition("LIB");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Milieu défensif");
            item.setCodPosition("MDF");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Milieu offensif");
            item.setCodPosition("MO");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Milieu gauche");
            item.setCodPosition("MG");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Milieu droit");
            item.setCodPosition("MD");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Avant centre");
            item.setCodPosition("AC");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Ailier gauche");
            item.setCodPosition("AG");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Ailier droit");
            item.setCodPosition("AD");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Second attaquant");
            item.setCodPosition("SA");
            ejbPosition.create(item);
            listPosition.add(item);

            item = new FamPosition();
            item.setLibPosition("Meneur de jeu");
            item.setCodPosition("MJ");
            ejbPosition.create(item);
            listPosition.add(item);
        } catch (Exception e) {
            String msg = "create position failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createTypMatch() {
        try {
            FamTypMatch item = new FamTypMatch();
            item.setLibTypMatch("Défault");
            item.setCodTypMatch("DEF");
            ejbTypMatch.create(item);
            listTypMatch.add(item);

            item = new FamTypMatch();
            item.setLibTypMatch("Coupe");
            item.setCodTypMatch("Cp");
            item.setExtraTime(Boolean.TRUE);
            item.setPenalties(Boolean.TRUE);
            ejbTypMatch.create(item);
            listTypMatch.add(item);

            item = new FamTypMatch();
            item.setLibTypMatch("Corpo");
            item.setCodTypMatch("CORP");
            item.setInfiniteSubs(Boolean.TRUE);
            ejbTypMatch.create(item);
            listTypMatch.add(item);
        } catch (Exception e) {
            String msg = "create typMatch failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createEvents() {
    }

    private void createWorkout() {
        try {
            FamWorkout item = new FamWorkout();
            DateTime dt = new DateTime();
            item.getFamEvent().setDtEvent(dt.toDate());
            item.getFamEvent().setDuration(120);
            item.getFamEvent().setLibEvent("E1");
            item.getFamEvent().setFamSeason(ejbSeason.getCurrentSeason());
            item.getFamEvent().setFamEventStatus(ejbEventStatus.getScheduled());

            ejbWorkout.create(item);
            listWorkout.add(item);
        } catch (Exception e) {
            String msg = "create workouts failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createSeasonCompetition() {
        try {
            for (FamSeason s : listSeason) {
                for (FamTypCompetition t : listTypCompetition) {
                    FamSeasonCompetition item = new FamSeasonCompetition();
                    item.setFamTypCompetition(t);
                    item.setFamSeason(s);
                    ejbSeasonCompetition.create(item);
                    listSeasonCompetition.add(item);
                }
            }
        } catch (Exception e) {
            String msg = "create seasonCompetition failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createUser() {
        try {
            FamUser item = new FamUser();
            item.setEmail("gbougeard@gmail.com");
            item.setFirstName("Gregory");
            item.setLastName("Bougeard");
            item.setOpenid(Boolean.TRUE);

            ejbUser.create(item);
            listUser.add(item);

            item = new FamUser();
            item.setEmail("toto@gmail.com");
            item.setFirstName("Toto");
            item.setLastName("Test");
            item.setOpenid(Boolean.FALSE);
            item.setPassword("toto");

            ejbUser.create(item);
            listUser.add(item);

            DataFactory df = new DataFactory();
            for (int i = 0; i < 200; i++) {
                item = new FamUser();
                item.setEmail(df.getEmailAddress().replaceAll(" ", "_"));
                item.setFirstName(df.getFirstName());
                item.setLastName(df.getLastName());
                item.setOpenid(Boolean.FALSE);
                item.setPassword("pwd");

                ejbUser.create(item);
                listUser.add(item);
            }

        } catch (Exception e) {
            String msg = "create user failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createProfilePlayer(FamPlayer item) {
        try {
            DataFactory df = new DataFactory();
            item.getCurrentProfile().setAtt(df.getNumberBetween(25, 80));
            item.getCurrentProfile().setDef(df.getNumberBetween(25, 80));
            item.getCurrentProfile().setVit(df.getNumberBetween(25, 80));
            item.getCurrentProfile().setPhy(df.getNumberBetween(25, 80));
            item.getCurrentProfile().setPui(df.getNumberBetween(25, 80));
            item.getCurrentProfile().setTec(df.getNumberBetween(25, 80));

            item.getCurrentProfile().setHeight(df.getNumberBetween(150, 210));
            item.getCurrentProfile().setWeight(df.getNumberBetween(65, 120));

            item.getCurrentPlayerSeason().setFamClub(df.getItem(listClub));
//            item.getCurrentPlayerSeason().setFamTeam(df.getItem(item.getCurrentPlayerSeason().getFamClub().getFamTeamList()));

            List<FamPlayerPosition> listPos = new ArrayList<FamPlayerPosition>();

            for (int i = 0; i < df.getNumberUpTo(6); i++) {
                FamPlayerPosition pos = new FamPlayerPosition();
                pos.setNumOrder(i);
                pos.setFamPlayer(item);
                pos.setFamPosition(df.getItem(listPosition));
                listPos.add(pos);
            }

            item.setFamPlayerPositionList(listPos);

            ejbPlayer.edit(item);
        } catch (Exception e) {
            String msg = "create ProfilePlayer failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void setEmailPlayer(FamPlayer item) {
//        StringBuilder sb = new StringBuilder();
        DataFactory df = new DataFactory();
//         sb.append(item.getFirstName()).append(".").append(item.getLastName()).append("@fam.org");
//         item.setEmail(sb.toString());
        item.setEmail(df.getEmailAddress().replaceAll(" ", "_"));
    }

    private void createPlayer() {
        try {
            DataFactory df = new DataFactory();

            FamPlayer item = new FamPlayer();
            item.setEmail("gbougeard@gmail.com");
            item.setFirstName("Gregory");
            item.setLastName("Bougeard");
            DateTime dt = new DateTime();
            item.setDtBirth(df.getBirthDate());
            item.setDtArrival(df.getDateBetween(dt.minusYears(15).toDate(), new Date()));
            item.setAddress("4, rue Roland Garros");
            item.setZipcode("31200");
            item.setCity("TOULOUSE");
            item.setNumLicense(1L);

            ejbPlayer.create(item);
            listPlayer.add(item);

            createProfilePlayer(item);

            item = new FamPlayer();
            item.setEmail("toto@gmail.com");
            item.setFirstName("Toto");
            item.setLastName("Test");
            item.setDtBirth(df.getBirthDate());
            item.setDtArrival(df.getDateBetween(dt.minusYears(15).toDate(), new Date()));
            item.setNumLicense(2L);

            ejbPlayer.create(item);
            listPlayer.add(item);

            createProfilePlayer(item);

            for (int i = 0; i < 200; i++) {
                item = new FamPlayer();
                item.setFirstName(df.getFirstName());
                item.setLastName(df.getLastName());
                item.setDtBirth(df.getBirthDate());
                dt = new DateTime();
                item.setDtArrival(df.getDateBetween(dt.minusYears(15).toDate(), new Date()));
                item.setNumLicense(new Long(df.getNumberUpTo(99999)));
                item.setAddress(df.getAddress());
                item.setZipcode(df.getNumberText(5));
                item.setCity(df.getCity());
                item.setTel(df.getNumberText(10));
                item.setIceContact(df.getName());
                item.setIceTel(df.getNumberText(10));
                setEmailPlayer(item);

                ejbPlayer.create(item);
                listPlayer.add(item);

                createProfilePlayer(item);

                ejbPlayer.edit(item);
            }

        } catch (Exception e) {
            String msg = "create player failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
            return;
        }
    }

    private void createStaffFunction() {
        try {
            FamStaffFunction item = new FamStaffFunction();
            item.setCodStaffFunction("P");
            item.setLibStaffFunction("Président");

            ejbStaffFunction.create(item);
            listStaffFunction.add(item);

            item = new FamStaffFunction();
            item.setCodStaffFunction("T");
            item.setLibStaffFunction("Trésorier");

            ejbStaffFunction.create(item);
            listStaffFunction.add(item);

            item = new FamStaffFunction();
            item.setCodStaffFunction("S");
            item.setLibStaffFunction("Secrétaire");

            ejbStaffFunction.create(item);
            listStaffFunction.add(item);

            item = new FamStaffFunction();
            item.setCodStaffFunction("C");
            item.setLibStaffFunction("Coach");

            ejbStaffFunction.create(item);
            listStaffFunction.add(item);

        } catch (Exception e) {
            String msg = "create staff Funtion failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createCountries() {
        try {
            ejbCountry.genData();

        } catch (Exception e) {
            String msg = "create Countries failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createStates() {
        try {
            ejbState.genData();

        } catch (Exception e) {
            String msg = "create States failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createProvinces() {
        try {
            ejbProvince.genData();

        } catch (Exception e) {
            String msg = "create Provinces failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    private void createCities() {
        try {
            ejbCity.genData();

        } catch (Exception e) {
            String msg = "create Cities failed";
            LogUtil.log(msg, Level.SEVERE, e);
            JsfUtil.addErrorMessage(e, msg);
        }
    }

    public Boolean getInitDone() {
        return initDone;
    }

    public void logMsg(String msg) {
        JsfUtil.addSuccessMessage("Init Data", msg);
        LogUtil.log(msg, Level.INFO, null);
//        CometContext.publish("chat", msg);
    }

    //    public void send(ActionEvent event){
//         CometContext.publish("chat", msg);
//    }
    public void testDrag(DragDropEvent e) {
        logMsg("testDrag " + e.getData());
    }

    public Integer getProgress() {
        if (progress > 100) {
            progress = 100;
        }
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void onComplete() {
        JsfUtil.addInfoMessage("Complete!", "InitData");
    }

    public void testData() {
        int i = 0;
        i++;
        progress = i % 1000;
    }
}
