#ifndef MODEL_HPP
#define MODEL_HPP

#include <iostream>
#include <vector>

class Data {

    public:
        static std::vector<std::string> composants_budget;
        static std::vector<std::string> autres_depenses_predefinies;
        static std::vector<std::string> depenses_fixes_predefinies;
        static std::vector<std::string> depenses_variables_predefinies;

    protected:
        // int getIndex(std::string var, std::vector<std::string>);
};

#endif