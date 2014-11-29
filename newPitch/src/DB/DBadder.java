package DB;

import BaseClasses.Ent;

interface DBadder {
	void add(AddUnit...addUnits);
	void add(Ent e);
}

