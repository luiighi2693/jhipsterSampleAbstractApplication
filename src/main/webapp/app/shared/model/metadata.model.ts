export interface IMetadata {
    id?: string;
    code?: string;
    value?: string;
}

export class Metadata implements IMetadata {
    constructor(public id?: string, public code?: string, public value?: string) {}
}
