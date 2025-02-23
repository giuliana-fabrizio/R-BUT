CONFIG += debug
TEMPLATE = app
TARGET = app
QT = core gui widgets charts
HEADEARS += ./Fichiers_hpp/*.hpp
SOURCES += app.cpp ./Controller/*.cpp ./Data/*.cpp ./Model/*.cpp ./View/*.cpp