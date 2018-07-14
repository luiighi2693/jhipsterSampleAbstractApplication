export interface IPerson {
    id?: string;
    partyId?: string;
    attributes?: string;
}

export class Person implements IPerson {
    constructor(public id?: string, public partyId?: string, public attributes?: string) {}
}
