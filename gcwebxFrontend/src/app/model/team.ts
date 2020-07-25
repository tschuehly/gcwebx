import {Member} from './member';

export interface Team {

  teamId: number;
  teamName: string;
  game: string;
  generalInfo: string;
  members: Array<Member>;
}
