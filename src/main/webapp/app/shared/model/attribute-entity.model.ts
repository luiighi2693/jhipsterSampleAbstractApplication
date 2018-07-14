export const enum Type {
    STRING = 'STRING',
    INTEGER = 'INTEGER',
    DATE = 'DATE',
    BOOLEAN = 'BOOLEAN'
}

export interface IAttributeEntity {
    id?: string;
    code?: string;
    value?: string;
    type?: Type;
    metadata?: string;
}

export class AttributeEntity implements IAttributeEntity {
    constructor(public id?: string, public code?: string, public value?: string, public type?: Type, public metadata?: string) {}
}
