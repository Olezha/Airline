# Airline [![Build Status](https://travis-ci.org/Olezha/Airline.svg?branch=master)](https://travis-ci.org/Olezha/Airline) [![codecov](https://codecov.io/gh/Olezha/Airline/branch/master/graph/badge.svg)](https://codecov.io/gh/Olezha/Airline) [![Dependency Status](https://www.versioneye.com/user/projects/5a72e8a80fb24f5752c0f584/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5a72e8a80fb24f5752c0f584) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/d3e55562ac694966b123600019927b21)](https://www.codacy.com/app/o1ezha/Airline?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Olezha/Airline&amp;utm_campaign=Badge_Grade)
## Company that stores airplanes
### Instructions for launching
After cloning the repository,
run the following commands on the root folder of the repository

```sh
mvn clean package
java -jar target/airline-1.0.jar
```

### REST Usage
Add, edit, delete a aircraft

Method | MediaType | URL | Body | Description
------ | --------- | --- | ---- | -----------
POST | APPLICATION_JSON | /commuterliners /helicopters /wideBodyAirliners | { "name": "Skyvan SD-330", "seatingCapacity": 10, "carryingCapacityKg": 2000, "flightRangeKm": 10000, "fuelConsumptionLitersPerHour": 150 } | Add aircraft
PUT | APPLICATION_JSON | /commuterliners/{id} /helicopters/{id} /wideBodyAirliners/{id} | { "name": "Skyvan SD-330 2", "seatingCapacity": 20, "carryingCapacityKg": 4000, "flightRangeKm": 10000, "fuelConsumptionLitersPerHour": 150 } | Edit aircraft
DELETE | | /aircrafts/{id} | | Delete aircraft

### CLI Usage 
Enter ```help``` to list all commands
```sh
Airline (to display available commands type help)
airline>help
AVAILABLE COMMANDS

Airline Shell Controller
        add: Add aircraft
        cc: Carrying capacity of all the aircraft in the airline
        delete: Delete
        fuel: Airplanes corresponding to a given range of fuel consumption parameters
        mock: Simulate objects
        search: Search
        show: Show all aircraft
        sort: List of aircraft of the company sorted by flight range
        tc: Total capacity of all the aircraft in the airline

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error


airline>|
```

Here's the sample session:
```sh
Airline (to display available commands type help)
airline>show
┌──┬────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
└──┴────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>mock
airline>show
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│1 ╎COMMUTERLINER     ╎10              ╎2000                 ╎10000           ╎150                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│2 ╎HELICOPTER        ╎18              ╎800                  ╎3050            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│3 ╎WIDE_BODY_AIRLINER╎200             ╎18000                ╎14300           ╎555                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│5 ╎HELICOPTER        ╎4               ╎2110                 ╎2300            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│7 ╎COMMUTERLINER     ╎101             ╎2014                 ╎1675            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│8 ╎HELICOPTER        ╎9               ╎900                  ╎1900            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│9 ╎WIDE_BODY_AIRLINER╎1020            ╎12000                ╎10100           ╎1600                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│10╎WIDE_BODY_AIRLINER╎555             ╎14500                ╎9900            ╎2500                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│12╎HELICOPTER        ╎24              ╎2600                 ╎900             ╎630                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│14╎COMMUTERLINER     ╎189             ╎1600                 ╎19000           ╎2250                 │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>tc
3084
airline>cc
75924
airline>fuel 800 1400
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>sort
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│12╎HELICOPTER        ╎24              ╎2600                 ╎900             ╎630                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│7 ╎COMMUTERLINER     ╎101             ╎2014                 ╎1675            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│8 ╎HELICOPTER        ╎9               ╎900                  ╎1900            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│5 ╎HELICOPTER        ╎4               ╎2110                 ╎2300            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│2 ╎HELICOPTER        ╎18              ╎800                  ╎3050            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│10╎WIDE_BODY_AIRLINER╎555             ╎14500                ╎9900            ╎2500                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│1 ╎COMMUTERLINER     ╎10              ╎2000                 ╎10000           ╎150                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│9 ╎WIDE_BODY_AIRLINER╎1020            ╎12000                ╎10100           ╎1600                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│3 ╎WIDE_BODY_AIRLINER╎200             ╎18000                ╎14300           ╎555                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│14╎COMMUTERLINER     ╎189             ╎1600                 ╎19000           ╎2250                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>sort -desc
┌──┬──────────────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type              ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│6 ╎WIDE_BODY_AIRLINER╎777             ╎400                  ╎19500           ╎1150                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│14╎COMMUTERLINER     ╎189             ╎1600                 ╎19000           ╎2250                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│3 ╎WIDE_BODY_AIRLINER╎200             ╎18000                ╎14300           ╎555                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│13╎WIDE_BODY_AIRLINER╎8               ╎8950                 ╎12500           ╎1340                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│9 ╎WIDE_BODY_AIRLINER╎1020            ╎12000                ╎10100           ╎1600                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│1 ╎COMMUTERLINER     ╎10              ╎2000                 ╎10000           ╎150                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│10╎WIDE_BODY_AIRLINER╎555             ╎14500                ╎9900            ╎2500                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│11╎COMMUTERLINER     ╎19              ╎4500                 ╎5675            ╎1001                 │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│2 ╎HELICOPTER        ╎18              ╎800                  ╎3050            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│5 ╎HELICOPTER        ╎4               ╎2110                 ╎2300            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│8 ╎HELICOPTER        ╎9               ╎900                  ╎1900            ╎120                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│7 ╎COMMUTERLINER     ╎101             ╎2014                 ╎1675            ╎350                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│4 ╎COMMUTERLINER     ╎150             ╎5550                 ╎1000            ╎990                  │
├╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│12╎HELICOPTER        ╎24              ╎2600                 ╎900             ╎630                  │
└──┴──────────────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>add HELICOPTER 1 2 3 4
airline>fuel 0 10
┌──┬──────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type      ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│15╎HELICOPTER╎1               ╎2                    ╎3               ╎4                    │
└──┴──────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>help add


NAME
	add - Add aircraft

SYNOPSYS
	add [-aircraft-type] aircraft-type  [[-seating-capacity] int]  [[-carrying-capacity-kg] int]  [[-flight-range-km] int]  [[-fuel-consumption-liters-per-hour] int]  

OPTIONS
	-aircraft-type  aircraft-type
		Type [WIDE_BODY_AIRLINER|COMMUTERLINER|HELICOPTER]
		[Mandatory]

	-seating-capacity  int
		Seating capacity
		[Optional, default = 0]

	-carrying-capacity-kg  int
		Carrying capacity (kg)
		[Optional, default = 0]

	-flight-range-km  int
		Flight range (km)
		[Optional, default = 0]

	-fuel-consumption-liters-per-hour  int
		Fuel consumption (liters per hour)
		[Optional, default = 0]



airline>add HELICOPTER -seating-capacity 8
airline>fuel 0 10
┌──┬──────────┬────────────────┬─────────────────────┬────────────────┬─────────────────────┐
│ID╎Type      ╎Seating capacity╎Carrying capacity, Kg╎Flight range, Km╎Fuel consumption, L/h│
├──┼──────────┼────────────────┼─────────────────────┼────────────────┼─────────────────────┤
│15╎HELICOPTER╎1               ╎2                    ╎3               ╎4                    │
├╌╌┼╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┼╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┤
│16╎HELICOPTER╎8               ╎0                    ╎0               ╎0                    │
└──┴──────────┴────────────────┴─────────────────────┴────────────────┴─────────────────────┘

airline>exit

Process finished with exit code 0
```
