package sanchez.Admin;

import sanchez.Util.BadCommandException;
import sanchez.Util.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class invite implements Command
{
    @Override
    public void run(MessageReceivedEvent e, String[] args) throws BadCommandException
    {
        e.getChannel().sendMessage("https://discordapp.com/api/oauth2/authorize?client_id=267149584986275840&scope=bot&permissions=0").queue();
    }

    @Override
    public int getPermLevel()
    {
        return 1;
    }
}
