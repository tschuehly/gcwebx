import {Team} from './team';

export interface Match {


  id: number;
  date: Date;
  hometeam: Team;
  title: string;
  opponent: string;
  opponentLogo: string;
  scoreHome: number;
  scoreOpponent: number;
}
