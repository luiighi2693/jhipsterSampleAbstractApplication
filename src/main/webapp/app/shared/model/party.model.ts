export interface IParty {
    id?: string;
    contextId?: string;
    partyId?: string;
    attributes?: string;
    listOfRoles?: string;
    listOfIdentifications?: string;
    listOfNames?: string;
}

export class Party implements IParty {
    constructor(
        public id?: string,
        public contextId?: string,
        public partyId?: string,
        public attributes?: string,
        public listOfRoles?: string,
        public listOfIdentifications?: string,
        public listOfNames?: string
    ) {}
}
